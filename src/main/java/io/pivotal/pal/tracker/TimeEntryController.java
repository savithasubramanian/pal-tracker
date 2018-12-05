package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {



    InMemoryTimeEntryRepository inTe = new InMemoryTimeEntryRepository();

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {





        TimeEntry entry =inTe.create(timeEntryToCreate);

        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

@GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
    TimeEntry entry = new TimeEntry();
    try {
       entry = inTe.find(timeEntryId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new ResponseEntity<TimeEntry>( entry, HttpStatus.OK);

    }

    public ResponseEntity<List<TimeEntry>> list() {
        return null;
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        TimeEntry entry = new TimeEntry();
        try {
            entry = inTe.find(timeEntryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TimeEntry>( entry, HttpStatus.OK);

    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        return null;
    }

}


