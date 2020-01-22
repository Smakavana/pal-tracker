package io.pivotal.pal.tracker;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
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
    public void delete(long id) {
        timeEntries.remove(id);
    }


}
