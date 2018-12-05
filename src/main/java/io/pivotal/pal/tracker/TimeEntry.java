package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {


    public TimeEntry() {

    }


    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;
    private long id;


    private long timeEntryId;


    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {

        this.setProjectId(projectId);
        this.setUserId(userId);
        this.setDate(date);
        this.setHours(hours);

    }

    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate date, int hours) {

        this.setId(timeEntryId);
        this.setProjectId(projectId);
        this.setUserId(userId);
        this.setDate(date);
        this.setHours(hours);
    }


    public Long getId() {
        return this.id;
    }
}
