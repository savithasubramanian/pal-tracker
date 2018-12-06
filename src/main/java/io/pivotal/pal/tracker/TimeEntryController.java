package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {


    private TimeEntryRepository inTe;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo ,
                               MeterRegistry meterRegistry) {
        this.inTe = timeEntriesRepo;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    //InMemoryTimeEntryRepository inTe = new InMemoryTimeEntryRepository();



    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {


        actionCounter.increment();
        timeEntrySummary.record(inTe.list().size());

        TimeEntry entry =inTe.create(timeEntryToCreate);

        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

@GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
    TimeEntry entry = new TimeEntry();
       entry = inTe.find(timeEntryId);
    if (entry != null) {
        actionCounter.increment();
       return new ResponseEntity<TimeEntry>( entry, HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity<>(inTe.list(), HttpStatus.OK);

    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry entry = new TimeEntry();

            entry = inTe.update(timeEntryId,expected);

            if (entry != null) {
                actionCounter.increment();
                return new ResponseEntity<>(entry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

         }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        inTe.delete(timeEntryId);
        actionCounter.increment();
        timeEntrySummary.record(inTe.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


