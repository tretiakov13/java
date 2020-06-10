package ua.khpi.oop.tretiakov14;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main implements Serializable {

    public static void fileRead() throws IOException, ParserConfigurationException, TransformerException, InterruptedException, 
    ExecutionException, TimeoutException {
        LinkedContainer<Event> linkedContainer = new LinkedContainer<Event>();

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

        regCheck(date, startTime, duration, venue, description);


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
        
        
        linkedContainer.addFirst(firstEvent);
        //linkedContainer.addLast(secondEvent);
        //linkedContainer.addLast(thirdEvent);
        System.out.println("Container size");
        System.out.println(linkedContainer.size());

        //choise(linkedContainer);
        //serialize(linkedContainer);
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        
        for(int i = 0; i<99999;i++) {
        	linkedContainer.addLast(secondEvent);
        }
        
        // Начало потока с огранечением по времени

        long begin = System.currentTimeMillis();

        Threads.MyTread1 myTread1 = new Threads.MyTread1();
        myTread1.set(linkedContainer);
        Threads.MyThread2  myThread2 = new Threads.MyThread2();
        myThread2.set(linkedContainer);
        Threads.MyThread3 myThread3 = new Threads.MyThread3();
        myThread3.set(linkedContainer);



        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Boolean> future = executorService.submit(myTread1);
        Future<Boolean> future1 = executorService.submit(myThread2);
        Future<Boolean> future2 = executorService.submit(myThread3);
        future.get();
        future1.get();
        future2.get();
        future.cancel(true);
        future1.cancel(true);
        future2.cancel(true);
        executorService.shutdown();
        try {
// В следующей строке первым параметром идет кол-во секунд сколько будет выполняться поток
            future.get(2000, TimeUnit.SECONDS);
        } catch (TimeoutException ex) {
            future.cancel(true);
        }
        executorService.shutdown();

        long finnish = System.currentTimeMillis();

        long result = finnish - begin;
        System.out.println("Time parralell threads was working " + result + " Milliseconds");


        // Конец потока с ограничением по времени

        // Два потока без ограничения по времени

        long start = System.currentTimeMillis();

        /*FirstThread threadFirst = new FirstThread(linkedContainer);
        ExecutorService executorServiceFirst = Executors.newFixedThreadPool(1);
        executorServiceFirst.submit(threadFirst);
        executorServiceFirst.shutdown();

        SecondThread threadSecond = new SecondThread(linkedContainer);
        ExecutorService executorServiceSecond = Executors.newFixedThreadPool(1);
        executorServiceSecond.submit(threadSecond);
        executorServiceSecond.shutdown();


        ThirdThread threadThird = new ThirdThread(linkedContainer);
        ExecutorService executorServiceThird = Executors.newFixedThreadPool(1);
        executorServiceThird.submit(threadThird);
        executorServiceThird.shutdown();*/
        
        FirstThread ft = new FirstThread(linkedContainer);
        ft.run();
        SecondThread st = new SecondThread(linkedContainer);
        st.run();
        ThirdThread th = new ThirdThread(linkedContainer);
        th.run();

        long stop = System.currentTimeMillis();
        long res = stop - start;

        System.out.println("Time consecutive threads was working  = " + res + " milliseconds");


        linkedContainer.addLast(secondEvent);
        linkedContainer.addLast(thirdEvent);

        // конец потоков без ограничения по времени
        
        // Поиск элемента соответствующего заданым критериям
        choise(linkedContainer);

    }
    
    public static void choise(LinkedContainer<Event> linkedContainer) {
    	for(Event ev : linkedContainer)
        {
    		if (ev.getData().compareTo("2017-01-01") > 0 && ev.getVenue() == "Kharkov" && ev.getDuration() >= 1440) {
    			System.out.println(ev);
			}
        }
    }

    public static void serialize(LinkedContainer<Event> linkedContainer) throws IOException, ParserConfigurationException, 
    TransformerException{
        XmlRead xmlRead = new XmlRead();
        XmlWrite xmlWrite = new XmlWrite();
        xmlWrite.write(linkedContainer,"XML.xml");

        LinkedContainer<Event> newXml = XmlRead.read("XML.xml");

        for(Event e : newXml )
        {
            System.out.println(e);
        }
       

    }
    
    public static void regCheck(String date, String startTime, int duration, String venue, String description)
    {
    	// Дата в формате dd-mm-yyyy
        if(date.matches("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])") == true)
        {
            System.out.println("OK");
        }
        else
        {
            System.out.println("Change date");
        }
        if(startTime.matches("^([0-1]\\d|2[0-3])(:[0-5]\\d)$"))
        {
            System.out.println("OK");
        }else{System.out.println("Change time");}
    }


    public static void manual() throws IOException, ClassNotFoundException, FileNotFoundException, TransformerException, 
    ParserConfigurationException {
        System.out.println("U have chosen manual mode");
        int choose;

        LinkedContainer<Event> linkedContainer = new LinkedContainer<>();
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
            System.out.println("6. Convert to String");
            System.out.println("7. Serialize ");
            System.out.println("8. Deserialize ");
            System.out.println("9. Xml serialize");
            System.out.println("10. Xml deserialize");
            System.out.println("11. Kharkov events");
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
                	System.out.println(linkedContainer.size());
                    linkedContainer.addLast(event1);
                    System.out.println(linkedContainer.size());
                    for(Event tmp : linkedContainer)
                    {
                        System.out.println(tmp);
                    }
                    break;
                case 3:
                	System.out.println(linkedContainer.size());
                	System.out.println("Еnter index: ");
                	int index = in.nextInt();
                    linkedContainer.removeByIndex(index);
                    System.out.println(linkedContainer.size());
                    break;
                case 4:
                	linkedContainer.clean();
                    System.out.println(linkedContainer.size());
                    break;
                case 5:
                	Object []arr = linkedContainer.toArray().toArray();
                    for(int i=0; i<linkedContainer.size();i++)
                    {
                        System.out.println(arr[i]);
                    }
                    break;
                case 6:
                	linkedContainer.toString();

                    break;
                case 7:
                	ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("event.txt"));
                    objectOutputStream.writeObject(linkedContainer);
                    objectOutputStream.close();

                    break;
                case 8:
                	ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("event.txt"));
                    LinkedContainer<Event> newTravels = (LinkedContainer<Event>) objectInputStream.readObject();
                    objectInputStream.close();

                    for (Event e : newTravels) {
                        System.out.println(e);
                    }
                    break;
                case 9:
                	XmlWrite xmlWrite = new XmlWrite();
                	xmlWrite.write(linkedContainer,"XML.xml");

                    break;
                case 10:
                	LinkedContainer<Event> newXml = XmlRead.read("XML.xml");

                    for(Event e : newXml )
                    {
                        System.out.println(e);
                    }
                	break;
                case 11:
                	choise(linkedContainer);
                	break;
                default:
                	System.out.println("Exit");
                    break;
            }}while(choose!=0);

    }



    public static void main(String args[]) throws IOException, ParserConfigurationException, TransformerException, 
    ClassNotFoundException, InterruptedException, ExecutionException, TimeoutException {

        if(args[0].equals("-auto"))
        {

            System.out.println("U chose auto mode.");
            System.out.println("1. Reading from file");
            fileRead();

        }
        else
        {
            manual();
        }
    }
}

