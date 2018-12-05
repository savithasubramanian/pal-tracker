package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long,TimeEntry> DbTimeEntry = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        Long id = DbTimeEntry.size() + 1L;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        DbTimeEntry.put(id,newTimeEntry);

        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        TimeEntry rettimeentry = DbTimeEntry.get(timeEntryId);
        return  rettimeentry;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(DbTimeEntry.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {

        TimeEntry updatedEntry = new TimeEntry(
                eq,
                any.getProjectId(),
                any.getUserId(),
                any.getDate(),
                any.getHours()
        );

        DbTimeEntry.replace(eq,updatedEntry);

        return updatedEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        DbTimeEntry.remove(timeEntryId);

    }

}
