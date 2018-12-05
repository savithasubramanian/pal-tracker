package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("1", "uno");
        TimeEntry tt = timeEntryRepository.create(timeEntryToCreate);
        ResponseEntity re = new ResponseEntity<>(tt, headers, HttpStatus.CREATED);

        return re;

    }
    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("1", "uno");
        TimeEntry tt = timeEntryRepository.find(timeEntryId);
        System.out.print("%%%%%%%%%%%%%%%%%%%%%read read read%%%%%%%%%%%%%%%%%%%%%%%Rrrrrrrrrrrrrrrrrrrrrrrrrrrrr%%%%%%%%6666666666666666666");
        if(tt == null) {
            ResponseEntity re = new ResponseEntity<>(tt, headers, HttpStatus.NOT_FOUND);
            return re;
        }

        ResponseEntity re = new ResponseEntity<>(tt, headers, HttpStatus.OK);

        return re;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("1", "uno");
        TimeEntry tt = timeEntryRepository.update(timeEntryId, expected);


        if(tt == null) {
            ResponseEntity re = new ResponseEntity<>(tt, headers, HttpStatus.NOT_FOUND);
            return re;
        }
        ResponseEntity re = new ResponseEntity<>(tt, headers, HttpStatus.OK);

        return re;


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("1", "uno");

        boolean pp = timeEntryRepository.delete(timeEntryId);
        ResponseEntity re = new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        return re;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("1", "uno");
        List<TimeEntry> tt = timeEntryRepository.list();

        ResponseEntity re = new ResponseEntity<>(tt, headers, HttpStatus.OK);

        return re;
    }
}
