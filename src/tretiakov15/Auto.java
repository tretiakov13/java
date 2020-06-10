package ua.khpi.oop.tretiakov15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Auto {
	public void auto()throws FileNotFoundException{ 

		File file = new File("file.txt");

	    Scanner scanner = new Scanner(file);

	    String line = scanner.nextLine();
	    String[] array = line.split(" ");
	    
	    // Участники
	    Participant par1[] = new Participant[2];
		par1[0] = new Participant("Роман","Жолин",25);
		par1[1] = new Participant("Евгений","Розумовский",24);
		
		Participant[] par2 = new Participant[2];
		par2[0] = new Participant("Игнат", "Третьяков", 18);
		par2[1] = new Participant("Ростислав", "Малахов", 19);
		
	    String date = null;
	    String startTime = null;
	    int duration = 0;
	    String venue = null;
	    String description = null;


	    for (int i = 0; i < array.length; i++) {
	        date = array[0].toString();
	        startTime = array[1].toString();
	        duration = Integer.parseInt(array[2]);
	        venue = array[3].toString();
	        description = array[4].toString();
	    }



	    Event firstEvent = new Event(date, startTime, duration, venue, description, par1);
	    Event secondEvent = new Event("2019-12-22", "10:00", 1000, "Kharkov", "Corporate", par2);
	    Event thirdEvent = new Event("2017-12-22", "10:00", 1440, "Kharkov","Corporate", par1);
	    
	    
	    Event[] events = {firstEvent, secondEvent, thirdEvent};


	    System.out.println("SORT BY DATE");
	    Arrays.sort(events);

	    for (Event e : events) {
	        System.out.println(e);
	    }
	    
	    
	    System.out.println("SORT BY DURATION");
	    ComparatorDuration comparatorDuration = new ComparatorDuration();       
	    Arrays.sort(events, comparatorDuration);
	    for(Event tmpss : events)
	    {
	        System.out.println(tmpss);
	    }


	    System.out.println("SORT BY PARTICIPANT");
	    ComparatorParticipant comparatorParticipant = new ComparatorParticipant();
	    Arrays.sort(events, comparatorParticipant);

	    for(Event tmpp : events)
	    {
	        System.out.println(tmpp);
	    }
	    
	    
	    ArrayList arrayList = new ArrayList();
	    arrayList.add(firstEvent);
	    arrayList.add(secondEvent);
	    arrayList.add(thirdEvent);
	    //linkedContainer.addFirst(firstEvent);
	    //linkedContainer.addLast(secondEvent);
	    //linkedContainer.addLast(thirdEvent);
	    System.out.println("Container size");
	    System.out.println(arrayList.size());

	}
}