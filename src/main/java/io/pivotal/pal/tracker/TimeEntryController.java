package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
@RequestMapping("/time-entries")
@RestController
public class TimeEntryController {

    private long timeEntryId = 1L;
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        TimeEntry response =  this.timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id){
        TimeEntry timeEntry = timeEntryRepository.find(id);

        return timeEntry!=null ? new ResponseEntity<>(timeEntry,HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>>list(){

        List<TimeEntry> timeEntries = timeEntryRepository.list();
        return  new ResponseEntity<>(timeEntries,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry){

        TimeEntry entryFound  =  timeEntryRepository.update(id,timeEntry);
        if(entryFound!=null) {

            return new ResponseEntity<>(entryFound, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

      //  TimeEntry timeEntry = timeEntryRepository.find(id);
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();



}
}
