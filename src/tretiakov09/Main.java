package ua.khpi.oop.tretiakov09;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.util.Iterator;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void switcher(LinkedContainer linkedContainer, Event event, XmlWrite xmlWrite, XmlRead xmlRead) throws IOException, 
    ClassNotFoundException, TransformerException, ParserConfigurationException {
        int choose;

        do{
            System.out.println("Choose action ");
            Scanner in = new Scanner(System.in);
            System.out.println("0. Exit");
            System.out.println("1. Add elem");
            System.out.println("2. Del elem");
            System.out.println("3. Clear container ");
            System.out.println("4. Convert to Array ");
            System.out.println("5. Convert to String");
            System.out.println("6. Create new element ");
            System.out.println("7. Serialize ");
            System.out.println("8. Deserialize ");
            System.out.println("9. Xml serialize");
            System.out.println("10. Xml deserialize");
            choose = in.nextInt();
            switch (choose) {
                case 1:
                    System.out.println(linkedContainer.size());
                    linkedContainer.addLast(event);
                    System.out.println(linkedContainer.size());
                    break;
                case 2:
                    System.out.println(linkedContainer.size());
                	System.out.println("�nter index: ");
                	int index = in.nextInt();
                    linkedContainer.removeByIndex(index);
                    System.out.println(linkedContainer.size());
                    break;
                case 3:
                    linkedContainer.clean();
                    System.out.println(linkedContainer.size());
                    break;
                case 4:

                //  linkedContainer.toArray();
                //	System.out.println(linkedContainer.getElementByIndex(0));
                	
                    Object []arr = linkedContainer.toArray().toArray();
                    for(int i=0; i<linkedContainer.size();i++)
                    {
                        System.out.println(arr[i]);
                    }
                    break;
                case 5:
                	linkedContainer.toString();
                	
                	break;
                case 6:
                	Participant[] par2 = new Participant[2];
            		par2[0] = new Participant("�����", "���������", 18);
            		par2[1] = new Participant("���������", "�������", 19);
            		//par2[2] = new Participant("�����", "�������", 17);
            		
                    Scanner din = new Scanner(System.in);
                    Scanner cin = new Scanner(System.in);
                    System.out.println("Enter date");
                    String date = din.nextLine();
                    System.out.println("Enter start time(format HH:MM)");
                    String startTime=din.nextLine();
                    System.out.println("Enter duration");
                    int duration=cin.nextInt();
                    System.out.println("Enter venue");
                    String venue=din.nextLine();
                    System.out.println("Enter description");
                    String description=din.nextLine();
                    event = new Event(date,startTime,duration,venue,description,par2);
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
                xmlWrite.write(linkedContainer,"XML.xml");

                    break;
                case 10:

                    LinkedContainer<Event> newXml = XmlRead.read("XML.xml");

                    for(Event e : newXml )
                    {
                        System.out.println(e);
                    }

                    //Object []array = newXml.toArray().toArray();
                    //for(int i=0; i<newXml.size();i++)
                    //{
                     //   System.out.println(array[i]);
                    //}

                    break;
                default:
                	System.out.println("Exit");
                    break;
            }}while(choose!=0);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, TransformerException, ParserConfigurationException {

        //�������� ������ ������ �����
        LinkedContainer<Event> linkedContainer = new LinkedContainer<Event>();
        XmlWrite xmlWrite = new XmlWrite();
        XmlRead xmlRead = new XmlRead();
        //�������� �������
        Participant par1[] = new Participant[2];
		par1[0] = new Participant("�����","�����",25);
		par1[1] = new Participant("�������","�����������",24);
        Scanner din = new Scanner(System.in);
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter date");
        String date = din.nextLine();
        System.out.println("Enter start time(format HH:MM)");
        String startTime=din.nextLine();
        System.out.println("Enter duration");
        int duration=cin.nextInt();
        System.out.println("Enter venue");
        String venue=din.nextLine();
        System.out.println("Enter description");
        String description=din.nextLine();
        Event event = new Event(date,startTime,duration,venue,description,par1);

        
        switcher(linkedContainer,event,xmlWrite, xmlRead);

    }
}
