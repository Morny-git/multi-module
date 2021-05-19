package com.mx.dataSec;


import com.mx.dataSec.conf.kafka.Constants;
import com.mx.dataSec.data.DataPipeline;
import com.mx.dataSec.utils.JedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
//借助于spring的特性 实现钩子@Component  @PreDestroy
@Component
public class TaskShutdownHook {

    private final static Logger trace = LoggerFactory.getLogger("trace");

    @Autowired
    private JedisUtils jedisUtils;
    @Autowired
    private DataPipeline dataPipeline;

    @PreDestroy
    public void run() {

        if (!dataPipeline.isEmpty()) {
            while (true){
                String poll = dataPipeline.poll(1);
                if (poll != null){
                    jedisUtils.lpush(Constants.REDIS_LIST_BACK,poll);
                    System.out.println("back data："+poll);
                }else {
                    return;
                }
            }
        }
        System.out.println("ShutdownHook fired.backup data!!!!!!!!!");
    }

}
