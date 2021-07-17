package freakyWecker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;







public class XMLReadWrite {	
	public saveSettings readXML() 
	{
		saveSettings sS= new saveSettings();
		
		
			
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
	        try {
	            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("test.xml"));
	            while(xmlEventReader.hasNext()){
	                XMLEvent xmlEvent = xmlEventReader.nextEvent();
	               if (xmlEvent.isStartElement()){
	                   StartElement startElement = xmlEvent.asStartElement();
	                   if(startElement.getName().getLocalPart().equals("SaveSettings")){
	              
	                   }
	                   //set the other varibles from xml elements
	                   else if(startElement.getName().getLocalPart().equals("Zeit")){
	                       xmlEvent = xmlEventReader.nextEvent();
	                       String temp = xmlEvent.asCharacters().getData();
	                       
	                       long time = Long.parseLong(temp);
	                       sS.setEndTime(time);
	                       
	                      // System.out.println("Zeit wurde geladen: "+temp);
	                       
	                   
	                   }
	                   
	                   else if(startElement.getName().getLocalPart().equals("Name")){
	                       xmlEvent = xmlEventReader.nextEvent();
	                       String temp = xmlEvent.asCharacters().getData();
	                       
	                       sS.setNameOfWecker(temp);
	                       
	                       //System.out.println("Name geladen: "+temp);
	                       
	                   }   
	                   
	                   else if(startElement.getName().getLocalPart().equals("Pfad")){
	                       xmlEvent = xmlEventReader.nextEvent();
	                       sS.setPfad(xmlEvent.asCharacters().getData());
	                   }else if(startElement.getName().getLocalPart().equals("DName")){
	                       xmlEvent = xmlEventReader.nextEvent();
	                       sS.setdName(xmlEvent.asCharacters().getData());
	                   }else if(startElement.getName().getLocalPart().equals("zeitZone")){
	                       xmlEvent = xmlEventReader.nextEvent();
	                       sS.setZeitZoneGespeichert(xmlEvent.asCharacters().getData());
	                   }else if(startElement.getName().getLocalPart().equals("language")){
	                       xmlEvent = xmlEventReader.nextEvent();
	                       sS.setLanguage(xmlEvent.asCharacters().getData());
	                   }
	               //if Employee end element is reached, add employee object to list
	               if(xmlEvent.isEndElement()){
	                   EndElement endElement = xmlEvent.asEndElement();
	                   if(endElement.getName().getLocalPart().equals("SaveSettings")){
	                      
	                   }
	               
	               }
	               }
	            }
	        } catch (FileNotFoundException | XMLStreamException e) {
	            e.printStackTrace();
	        }
		
	        //2
	       //System.out.println("---Test---");
	        
	        for (int i = 0;i<sS.sizeOfEndTimes();i++)
			{
				long yourmilliseconds = sS.getEndTime(i);
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
				
				sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
				//df.format(date1);
				
				Date resultdate = new Date(yourmilliseconds);
				//System.out.println(sS.getNameOfWecker(i)+sdf.format(resultdate));
				//System.out.println("long:"+resultdate.getTime());
			
			}    
	        
	        
		return sS;
		
	}
	
	void writeXML(saveSettings sS) throws FileNotFoundException, IOException, XMLStreamException
	{
		try (FileOutputStream fos = new FileOutputStream("test.xml")){
	        XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
	        XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(fos);
	        writer.writeStartDocument();
	        
	        writer.writeStartElement("SaveSettings");
	        
	        for(int i = 0; i< sS.sizeOfEndTimes();i++)
	        {
	        	writer.writeStartElement("Zeit");
	       

	        	writer.writeCharacters(Long.toString(sS.getEndTime(i)));
	        // write stuff
	        	writer.writeEndElement();
	        	
	        	 writer.writeStartElement("Name");
			       

	 	        writer.writeCharacters(sS.getNameOfWecker(i));
	 	        // write stuff
	 	        writer.writeEndElement();
	 	        
	        }
	       
	        writer.writeStartElement("Pfad");
		       

	        writer.writeCharacters(sS.getPfad());
	        // write stuff
	        writer.writeEndElement();
	        
	        
	        writer.writeStartElement("DName");
		       

	        writer.writeCharacters(sS.getdName());
	        // write stuff
	        writer.writeEndElement();
	       
	        writer.writeStartElement("zeitZone");
		       

	        writer.writeCharacters(sS.getZeitZoneGespeichert());
	        // write stuff
	        writer.writeEndElement();
	        
	        writer.writeStartElement("language");
		       

	        writer.writeCharacters(sS.getLanguage());
	        // write stuff
	        writer.writeEndElement();
	        
	        
	        writer.writeEndElement();
	    
	    }
	}
}
