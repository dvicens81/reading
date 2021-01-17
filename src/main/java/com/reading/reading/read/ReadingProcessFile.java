package com.reading.reading.read;

import java.io.IOException;
/**
 * Interface that is declared the read files method
 * 
 * All the classes to read a file will extends to this interface
 * @author 
 *
 */
public interface ReadingProcessFile {
	
	abstract void read(String fileName) throws IOException;

}
