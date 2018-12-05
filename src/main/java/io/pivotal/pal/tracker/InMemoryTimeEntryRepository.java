package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long,TimeEntry> DbTimeEntry = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry any) {

        DbTimeEntry.put(any.getId(),any);

        return any;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        TimeEntry rettimeentry = DbTimeEntry.get(timeEntryId);
        return  rettimeentry;
    }

    @Override
    public ResponseEntity<List<TimeEntry>> list() {
        return null;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        return null;
    }

    @Override
    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        return null;
    }

}
