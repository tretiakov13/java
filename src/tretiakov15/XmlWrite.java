package ua.khpi.oop.tretiakov15;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlWrite {
	static public void write(ArrayList<Event> linkedContainer, String fileName) throws ParserConfigurationException, TransformerFactoryConfigurationError, FileNotFoundException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        
        for(Event e : linkedContainer)
        {
        Element Event = doc.createElement("Event");
        Element Data = doc.createElement("Data");
        Element StartTime = doc.createElement("StartTime");
        Element Duration = doc.createElement("Duration");
        Element Venue = doc.createElement("Venue");
        Element Description = doc.createElement("Description");
        Element Participant = doc.createElement("Participant");
        
        ////////////////////////////////////
        
        doc.appendChild(Event);
        Event.appendChild(Data);
        Event.appendChild(StartTime);
        Event.appendChild(Duration);
        Event.appendChild(Venue);
        Event.appendChild(Description);
        
        Event.appendChild(Participant);
        
        /////////////////////////////////////
        
        Data.appendChild(doc.createTextNode(e.getData()));
        StartTime.appendChild(doc.createTextNode(e.getStartTime()));
        Duration.appendChild(doc.createTextNode(Integer.toString(e.getDuration())));
        Venue.appendChild(doc.createTextNode(e.getVenue()));
        Description.appendChild(doc.createTextNode(e.getDescription()));
        
        StringBuffer participant = new StringBuffer();
        for (int i = 0; i < e.getParticipants().length; i++)	{
              participant.append(e.getParticipants()[i].getName() + " ");
              participant.append(e.getParticipants()[i].getSurname() + " ");
              participant.append(e.getParticipants()[i].getAge() + " ");
        }
        Participant.appendChild(doc.createTextNode(participant.toString()));
        
        }
        
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileName)));
        
	}
}
