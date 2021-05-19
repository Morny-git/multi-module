package com.mx.event.EventMechanism;

public class TaskBreakEvent extends MyEvent {
    private final int exitCode;
    public TaskBreakEvent(Object source, int exitCode) {
        super(source);
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return this.exitCode;
    }
}
