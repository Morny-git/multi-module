package com.mx.event.EventMechanism;

import java.util.EventListener;
//事件监听器
public class MyListener implements EventListener {

    public void handleEvent(MyEvent event) {
        if (event instanceof TaskBreakEvent) {
            TaskBreakEvent event1 = (TaskBreakEvent) event;
            System.out.println("触发状态改变事件。。。");
            System.out.println("当前事件源状态为：" + event1.getExitCode());
        }

   }
}
