package ua.khpi.oop.tretiakov15;

import java.util.Comparator;

public class ComparatorDuration implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        if (o1.getDuration() > o2.getDuration()) {
			return 1;
		} 
        else if (o1.getDuration() == o2.getDuration()) {
			return 0;
		}
        {
        	return -1;
        }
    }

}