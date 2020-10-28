package com.QuickPollDemo.QuickPollDemo.dto.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developersMessage;
    private Map<String, List<ValidationError>> error = new HashMap<>();

    public Map<String, List<ValidationError>> getError() {
        return error;
    }

    public void setError(Map<String, List<ValidationError>> error) {
        this.error = error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDevelopersMessage() {
        return developersMessage;
    }

    public void setDevelopersMessage(String developersMessage) {
        this.developersMessage = developersMessage;
    }

}
