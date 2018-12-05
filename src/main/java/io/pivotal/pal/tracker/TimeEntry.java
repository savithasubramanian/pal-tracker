package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntry timeEntry = (TimeEntry) o;

        if (id != timeEntry.id) return false;
        if (projectId != timeEntry.projectId) return false;
        if (userId != timeEntry.userId) return false;
        if (hours != timeEntry.hours) return false;
        return date != null ? date.equals(timeEntry.date) : timeEntry.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + hours;
        return result;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", hours=" + hours +
                '}';
    }

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
