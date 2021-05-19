package com.mx.event.EventMechanism;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//事件源
public class MySource {
    private List<MyListener> listeners = new ArrayList<MyListener>();

    //添加任务监听器
    public void addListener(MyListener myListener) {
        listeners.add(myListener);
    }

    //当事件发生时，通知注册在事件源上的所有事件做出相应的反映
    public void notifyListener(MyEvent myEvent) {
        for (Iterator<MyListener> iterator = listeners.iterator(); iterator.hasNext(); ) {
            MyListener myListener = iterator.next();
            myListener.handleEvent(myEvent);
        }
    }
}
