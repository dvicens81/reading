package com.reading.reading.xml.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.reading.reading.dto.ReadingDto;
import com.reading.reading.dto.ClientDto;
/**
 * 
 * Read XML file and create a map of list of clients
 * @author 
 *
 */
public class ReadingHandler extends DefaultHandler{
	
	private Map<String, ClientDto> mapClients = new LinkedHashMap<>();
	private ClientDto clientXml;
	private ReadingDto readingClientXml;
	private boolean isCreated = false;
	
	@Override
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if("reading".equals(qName)) {			
			clientXml = this.getClientFromMap(attributes.getValue("clientID"));
			readingClientXml = new ReadingDto();
			readingClientXml.setPeriod(attributes.getValue("period"));
			isCreated = true;
		}
	}
	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
		if (isCreated) {
			readingClientXml.setReading(new Integer(new String(ch, start, length)));
			List<ReadingDto> listReading = clientXml.getReadingClient();
			listReading.add(readingClientXml);	
		}		
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	Optional.of(isCreated)
    			.filter(BooleanUtils::isTrue)
    			.ifPresent(created->{
					mapClients.computeIfAbsent(clientXml.getId(), k-> clientXml);
					isCreated = false;
				});
    }
    
    private ClientDto getClientFromMap(String id) {
    	return Optional.ofNullable(mapClients.get(id)).orElse(new ClientDto(id));
    }
    
    public Map<String, ClientDto> getMapClients(){
    	return this.mapClients;
    }
}
