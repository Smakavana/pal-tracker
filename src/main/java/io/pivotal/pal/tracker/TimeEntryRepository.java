package io.pivotal.pal.tracker;

import java.util.Collection;

public interface TimeEntryRepository {
    TimeEntry find(long timeEntryId);

    TimeEntry create(TimeEntry timeEntry);

    Collection<TimeEntry> list();

    TimeEntry update(long id, TimeEntry timeEntry);

    TimeEntry delete(long id);
}
