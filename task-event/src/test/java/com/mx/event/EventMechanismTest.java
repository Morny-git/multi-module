package com.mx.event;

import com.mx.event.EventMechanism.MyListener;
import com.mx.event.EventMechanism.MySource;
import com.mx.event.EventMechanism.TaskBreakEvent;

public class EventMechanismTest {
    public static void main(String[] args) {
        MySource mySource = new MySource();
        mySource.addListener(new MyListener());
        mySource.notifyListener(new TaskBreakEvent("msg",500));
    }
}
