package com.mx.dataSec.metric;

import io.prometheus.client.Histogram;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class HistogramMetric {

    private static double[] buckets = new double[]{0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,2.0};

    public static Map<String, Histogram.Timer> timerMap = new ConcurrentHashMap<>();
    //开始计时
    public static void startBulkTime(Histogram histogram,String label){
        Histogram.Timer timer = histogram.labels(label).startTimer();
        timerMap.put(label, timer);
    }

    //计时结束
    public static void endProductTime(String label){
        if (timerMap.containsKey(label)){
            timerMap.get(label).observeDuration();
        }
        timerMap.remove(label);
    }


    //创建Histogram
    private static Histogram createHistogram (String name , String help, String... labelNames){
        return Histogram.build().name(name).buckets(buckets).help(help).labelNames(labelNames).register();
    }

    public static void main(String[] args) throws InterruptedException {
        Histogram histogram = createHistogram("histogram_name","help","test_label");
        startBulkTime(histogram,"test_label");
        Thread.sleep(100);
        endProductTime("test_label");

        System.out.println(histogram.collect());

    }
}
