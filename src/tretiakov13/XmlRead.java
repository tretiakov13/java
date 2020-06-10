package ua.khpi.oop.tretiakov13;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlRead {
	static public LinkedContainer<Event> read(String fileName) throws IOException, ParserConfigurationException {
		LinkedContainer<Event> resEvent = new LinkedContainer<>();
		try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            System.out.println(root.getNodeName());
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList event = root.getChildNodes();
            
            String data = new String();
            String startTime = new String();
            int duration = 0;
            String venue = new String();
            String description = new String();
            Participant[] participants = new Participant[2];

            for (int i = 0; i < event.getLength(); i++){
                if(event.item(i).getNodeType() != Node.TEXT_NODE){
                    if(event.item(i).getNodeName() == "Data"){
                        data = event.item(i).getTextContent();
                    }
                    if(event.item(i).getNodeName() == "StartTime"){
                        startTime = event.item(i).getTextContent();
                    }
                    if(event.item(i).getNodeName() == "Duration"){
                        duration = Integer.valueOf(event.item(i).getTextContent());
                    }
                    if(event.item(i).getNodeName() == "Venue"){
                        venue = event.item(i).getTextContent();
                    }
                    if(event.item(i).getNodeName() == "Description"){
                        description = event.item(i).getTextContent();
                    }
                    if(event.item(i).getNodeName() == "Participant"){
                        String str[] = event.item(i).getTextContent().split(" ");
                        String name = new String();
                        String surname = new String();
                        int age = 0;
                        int j = 0;
                        for (int m = 0; m < str.length; m+=3){
                        	name = str[m];
                        	surname = str[m+1];
                        	age = Integer.valueOf(str[m+2]);
                            participants[j] = new Participant(name,surname,age);
                            j++;
                        }
                    }
                }
                Event e1 = new Event(data, startTime, duration, venue,description,participants);
                resEvent.addLast(e1); 
                
            }
            //Event e1 = new Event(data, startTime, duration, venue,description,participants);
            //resEvent.addLast(e1);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return resEvent;
	}
}
