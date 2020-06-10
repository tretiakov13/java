package ua.khpi.oop.tretiakov15;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;





public class Manual {
	
	public void switchCase()throws IOException, ClassNotFoundException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException { 
	System.out.println("U have chosen manual mode");
	int choose;

    ArrayList<Event> arrayList = new ArrayList<Event>();
    Event event1 = null;

    do{
    	System.out.println("Choose action ");
        Scanner in = new Scanner(System.in);
        System.out.println("0. Exit");
        System.out.println("1. Create new element");
        System.out.println("2. Add elem ");
        System.out.println("3. Del elem");
        System.out.println("4. Clear container ");
        System.out.println("5. Convert to Array ");
        System.out.println("6. Serialize ");
        System.out.println("7. Deserialize ");
        System.out.println("8. Xml serialize");
        System.out.println("9. Xml deserialize");
        System.out.println("10. Kharkov events");
        choose = in.nextInt();
        switch (choose) {
            case 1:
            	Participant[] par2 = new Participant[2];
        		par2[0] = new Participant("Игнат", "Третьяков", 18);
        		par2[1] = new Participant("Ростислав", "Малахов", 19);
        		//par2[2] = new Participant("Данил", "Макаров", 17);
        		
                Scanner din = new Scanner(System.in);
                Scanner cin = new Scanner(System.in);
                System.out.println("Enter date(format yyyy-mm-dd)");
                String date = din.nextLine();
                if(date.matches("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])")==true) {
                	System.out.println("OK");
                }else {System.out.println("NOT OK CHANGE DATE");date=din.nextLine();}
                System.out.println("Enter start time(format HH:MM)");
                String startTime=din.nextLine();
                if(startTime.matches("^([0-1]\\d|2[0-3])(:[0-5]\\d)$")==true) {
                	System.out.println("OK");
                }else {System.out.println("NOT OK CHANGE TIME");startTime=din.nextLine();}
                System.out.println("Enter duration");
                int duration=cin.nextInt();
                System.out.println("Enter venue");
                String venue=din.nextLine();
                System.out.println("Enter description");
                String description=din.nextLine();
                event1 = new Event(date,startTime,duration,venue,description,par2);
                break;

            case 2:
            	System.out.println(arrayList.size());
                arrayList.add(event1);
                System.out.println(arrayList.size());
                
                for(Event tmp : arrayList)
                {
                    System.out.println(tmp);
                }
                break;
            case 3:
            	System.out.println(arrayList.size());
            	System.out.println("Еnter index: ");
            	int index = in.nextInt();
                arrayList.remove(index);
                System.out.println(arrayList.size());
                break;
            case 4:
            	arrayList.clear();
                System.out.println(arrayList.size());
                break;
            case 5:
            	Object []arr = arrayList.toArray();
                for(int i=0; i<arrayList.size();i++)
                {
                    System.out.println(arr[i]);
                }
                break;

            case 6:
            	ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("event.txt"));
                objectOutputStream.writeObject(arrayList);
                objectOutputStream.close();

                break;
            case 7:
            	ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("event.txt"));
                ArrayList<Event> newEvents = (ArrayList<Event>) objectInputStream.readObject();
                objectInputStream.close();

                for (Event e : newEvents) {
                    System.out.println(e);
                }
                break;
            case 8:
            	XmlWrite xmlWrite = new XmlWrite();
            	xmlWrite.write(arrayList,"XML.xml");

                break;
            case 9:
            	ArrayList<Event> newXml = XmlRead.read("XML.xml");

                for(Event e : newXml )
                {
                    System.out.println(e);
                }
            	break;
            case 10:
            	for(Event ev : arrayList)
                {
            		if (ev.getData().compareTo("2017-01-01") > 0 && ev.getVenue() == "Kharkov" && ev.getDuration() >= 1440) {
            			System.out.println(ev);
        			}
                }
            	break;
            default:
            	System.out.println("Exit");
                break;
        }}while(choose!=0);
	}
}
