package com.marklux.mapper;

import com.marklux.domain.CalendarItem;

import java.util.Collection;
import java.util.List;

/**
 * Created by mark on 17/11/3.
 */
public interface CalendarItemMapper  {
    Collection<CalendarItem> getItems(long calendarId);
    int createItem(CalendarItem item);
    int createItems(List<CalendarItem> list);
    int updateItem(CalendarItem item);
    int deleteItem(CalendarItem item);
}
