package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry entryCreated = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(entryCreated, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        return new ResponseEntity(timeEntry, timeEntry != null? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        Collection<TimeEntry> timeEntries = timeEntryRepository.list();
        return new ResponseEntity(timeEntries, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry entryToUpdate) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, entryToUpdate);
        return new ResponseEntity(timeEntry, timeEntry != null? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(timeEntry, HttpStatus.NO_CONTENT);
    }
}
