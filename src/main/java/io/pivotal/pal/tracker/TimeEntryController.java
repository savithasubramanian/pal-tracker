package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {


    private TimeEntryRepository inTe;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.inTe = timeEntriesRepo;
    }

    //InMemoryTimeEntryRepository inTe = new InMemoryTimeEntryRepository();



    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {





        TimeEntry entry =inTe.create(timeEntryToCreate);

        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

@GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
    TimeEntry entry = new TimeEntry();
       entry = inTe.find(timeEntryId);
    if (entry != null) {
       return new ResponseEntity<TimeEntry>( entry, HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(inTe.list(), HttpStatus.OK);

    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry entry = new TimeEntry();

            entry = inTe.update(timeEntryId,expected);

            if (entry != null) {
                return new ResponseEntity<>(entry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

         }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        inTe.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


