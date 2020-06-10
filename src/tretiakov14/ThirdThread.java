package ua.khpi.oop.tretiakov14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ThirdThread implements Runnable {

    LinkedContainer<Event> linkedContainer;

    public ThirdThread(LinkedContainer<Event> linkedContainer)
    {
        this.linkedContainer = linkedContainer;
    }


    @Override
    public void run() {
        for(Event ev : linkedContainer)
        {
        	try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		if (ev.getData().compareTo("2017-01-01") > 0 && ev.getVenue() == "Kharkov" && ev.getDuration() >= 1440) {
    			System.out.println(ev);
			}
        }
    }

}