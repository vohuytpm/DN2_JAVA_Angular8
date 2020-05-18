package com.rikkeisoft.vn.model;

public class JobStatus {
    public final String id;
    public final boolean running;

    public JobStatus(String id, boolean running) {
        this.id = id;
        this.running = running;
    }
}
