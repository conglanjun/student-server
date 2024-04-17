package com.clj.student.service;

import java.util.HashMap;
import java.util.Map;

public enum ServiceStatus {
    SUBMITTED("SUBMITTED", "已提交"),
    PENDINGORDER("PENDINGORDER", "待接单"),
    RECEIVEDORDER("RECEIVEDORDER", "已接单"),
    REJECTORDER("REJECTORDER", "拒绝接单"),
    HANDLING("HANDLING", "维修中"),
    DONE("DONE", "已完成"),
    FINISHED("FINISHED", "评价完");

    private static final Map<String, ServiceStatus> BY_STATUS = new HashMap<>();
    private static final Map<String, ServiceStatus> BY_DISPLAY_STATUS = new HashMap<>();

    static {
        for (ServiceStatus ss:  values()) {
            BY_STATUS.put(ss.status, ss);
            BY_DISPLAY_STATUS.put(ss.displayStatus, ss);
        }
    }

    private final String status;
    private final String displayStatus;

    ServiceStatus(String status, String displayStatus) {
        this.status = status;
        this.displayStatus = displayStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public static ServiceStatus valueOfStatus(String status) {
        return BY_STATUS.get(status);
    }

    public static ServiceStatus valueOfDisplayStatus(String displayStatus) {
        return BY_STATUS.get(displayStatus);
    }
}
