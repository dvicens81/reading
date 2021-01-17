package com.reading.reading.read.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.xml.parsers.SAXParser;

import com.reading.reading.common.ReadingFile;
import com.reading.reading.print.ResultToPrint;
import com.reading.reading.read.ReadingProcessFile;
import com.reading.reading.xml.saxparser.ReadingSAXParser;

/**
 * 
 * Read XML FILE
 * 
 * @author 
 *
 */
public class ReadingXmlParser implements ReadingProcessFile{

	@Override
	public void read(String fileName) throws IOException {
		InputStream fileInputStream = ReadingFile.getInputStreamFromFileName(fileName);
		SAXParser parser = ReadingSAXParser.createSaxParser();
		Optional.ofNullable(parser)
				.ifPresent(saxParser->this.processFile(saxParser, fileInputStream));
		
	}
	
	private void processFile(SAXParser saxParser, InputStream fileInputStream) {
		ResultToPrint.executeResult(ReadingSAXParser.executeSaxParser(fileInputStream, saxParser));
	}
}
