package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    private AtomicLong nextId = new AtomicLong();

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry copy = new TimeEntry(nextId.incrementAndGet(), timeEntry.getProjectId(),
                timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(copy.getId(), copy);
        return timeEntries.get(copy.getId());
    }

    @Override

    public Collection<TimeEntry> list() {
        return timeEntries.values();
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry timeEntryToUpdate = timeEntries.get(id);
        if(timeEntryToUpdate != null){
            TimeEntry copy = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            timeEntries.put(timeEntryToUpdate.getId(), copy);
        }
        return timeEntries.get(id);
    }

    @Override
    public TimeEntry delete(long id) {
        return timeEntries.remove(id);
    }


}
