package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    List<TimeEntry> list = new ArrayList<>();

    long id = 0;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++id);

        list.add(timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setId(id);

        for (TimeEntry entry : list) {
            if(id == entry.getId()) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public List<TimeEntry> list() {
        System.out.print(list.size());
        return list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        timeEntry.setId(id);

        return timeEntry;
    }

    @Override
    public boolean delete(long id) {



        for (TimeEntry timeEntry : list) {
            if(id == timeEntry.getId()) {
                list.remove(timeEntry);
                return true;
            }
        }

        return false;
    }
}
