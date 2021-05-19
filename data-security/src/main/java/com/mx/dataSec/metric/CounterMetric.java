package com.mx.dataSec.metric;

import io.prometheus.client.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterMetric {
    //创建counter
    private static Counter createCounter(String name, String help, String... labelNames){
        return Counter.build().name(name).labelNames(labelNames).help(help).register();
    }

    private static void inc(Counter counter,String... labelName){
        inc(counter ,1.0D , labelName);
    }

    private static void inc(Counter counter,double amt,String... labelName){
        counter.labels(labelName).inc(amt);
    }

    public static void main(String[] args) {
        Counter counter= createCounter("counter_test","test_help","counter_test_label");
        inc(counter,"counter_test_label");
        System.out.println(counter.collect());

    }
}
