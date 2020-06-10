package ua.khpi.oop.tretiakov15;

import java.util.Comparator;

public class ComparatorParticipant implements Comparator<Event> 
{
	@Override
    public int compare(Event o1, Event o2)
    {
		if (o1.getParticipants().length > o2.getParticipants().length) {
			return 1;
		} 
        else if (o1.getParticipants().length == o2.getParticipants().length) {
			return 0;
		}
        {
        	return -1;
        }
    }
}
