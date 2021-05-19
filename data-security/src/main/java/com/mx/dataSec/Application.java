package com.mx.dataSec;

import com.mx.dataSec.handler.KafkaCousumerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;


@SpringBootApplication
public class Application implements CommandLineRunner {
    private final CountDownLatch keepAliveLatch = new CountDownLatch(1);
    private Thread keepAliveThread;
    public Application() {
        keepAliveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    keepAliveLatch.await();
                } catch (InterruptedException e) {
                    // bail out
                }
            }
        }, "DTS[keepAlive]");
        keepAliveThread.setDaemon(false);
    }
    @Autowired
    private KafkaCousumerHandler handler;

    public static void main(String[] args) {
//        Runtime.getRuntime().addShutdownHook(new Thread(new TaskShutdownHook1()));
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        applicationContext.addApplicationListener(new ApplicationListener<ExitCodeEvent>() {
            @Override
            public void onApplicationEvent(ExitCodeEvent exitCodeEvent) {
                System.out.println("The Application exited, cause of : " + exitCodeEvent.getExitCode());
            }
        });

    }
    @Override
    public void run(String... strings) throws Exception {
        //初始化
        setInit();
        //前置检测
        if (!PreTest()){
            System.exit(-1);
        }
        //启动处理线程
        start();

    }

    //初始化
    public void setInit() {
        // keep this thread alive (non daemon thread) until we shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                keepAliveLatch.countDown();
            }
        });

        System.out.println("arg[reserve] : " );

    }

    boolean PreTest(){
        //所有的加载已完成
        return true;
    }
    void start(){
        keepAliveThread.start();
        handler.start();
    }

}
