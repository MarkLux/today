package com.marklux.mapper;

import com.marklux.domain.CalendarItem;

import java.util.Collection;

/**
 * Created by mark on 17/11/3.
 */
public interface CalendarItemMapper  {
    Collection<CalendarItem> getItems(long calendarId);
    int createItem(CalendarItem item);
    int updateItem(CalendarItem item);
    int deleteItem(CalendarItem item);
}
