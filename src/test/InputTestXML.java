package test;

import static org.junit.Assert.*;

import java.io.File;

import input.XMLInput;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import data.Salesman;

public class InputTestXML {
	
	File testFile = new File("src/test_input_files/test-case-2-XML.xml");
	File nonExistantFile = new File("src/nonExistantFile.txt");
	File InvalidXMLParser  = new File("src/test_exception_cases/test-exception-case-1-XML.xml");	
	File MissingElementsXML= new File("src/test_exception_cases/test-exception-case-2-XML.xml");
	
	XMLInput inputFileXML;  
	XMLInput inputFileXML2; 
	XMLInput inputFileXML3;
	XMLInput inputFileXML4;

	Salesman salesman = new Salesman();
	
	 @Before
	    public void setUp() {
		 	inputFileXML = new XMLInput(testFile);
		 	inputFileXML2 = new XMLInput(nonExistantFile);
		 	inputFileXML3 = new XMLInput(InvalidXMLParser);
		 	inputFileXML4 = new XMLInput(MissingElementsXML);
	    }
	
	  @Test
	    public void testSuccessfulReadingAndParsing() throws SAXException {
		  	inputFileXML.readReceipts();
	        salesman = inputFileXML.getSalesman();

	        assertEquals("Vassileios Zarras", salesman.getName());
	        assertEquals("130456097", salesman.getAfm());
	    }
	       
	    @Test
	    public void testExceptionWhenFileDoesNotExist() {
	        try {
	        	inputFileXML2.readReceipts();
	        } catch (Exception exception) {
	            assertNotNull(exception.getMessage());
	            assertTrue(exception.getMessage().contains("Issues while creating DocumentBuilder"));
	        }
	    }

	    @Test
	    public void testSAXException() throws SAXException {
	        try {          
	        	inputFileXML3.readReceipts();   
	            
	        } catch (SAXException e) {
	            assertNotNull(e.getMessage());
	            assertTrue(e.getMessage().contains("SAX exception during parsing"));
	        }        
	    } 
	
	    @Test
	    public void testNullPointerException() throws SAXException {
	        try {          
	        	inputFileXML4.readReceipts();   
	            
	        } catch (NullPointerException e) {
	            assertNotNull(e.getMessage());
	            assertTrue(e.getMessage().contains("Null pointer exception when accessing XML node"));
	        }        
	    } 
	
	
	
	
}
