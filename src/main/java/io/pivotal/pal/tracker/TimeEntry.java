package io.pivotal.pal.tracker;


import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public long getId() {
        return id;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public TimeEntry(long l, long l1, long l2, LocalDate parse, int i) {
        this.id=l;
        this.projectId=l1;
        this.userId=l2;
        this.date=parse;
        this.hours=i;

    }

    public TimeEntry(long projectId, long userId, LocalDate parse, int i) {
        this.projectId=projectId;
        this.userId=userId;
        this.date=parse;
        this.hours=i;
    }

    public TimeEntry() {

    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TimeEntry)) {
            return false;
        }
        TimeEntry cc = (TimeEntry)o;
        return cc.id == id;
    }
    public int hashCode() {
        int result = 17;
        result = 31 * result + Integer.parseInt(projectId+"");
        return result;
    }

}
