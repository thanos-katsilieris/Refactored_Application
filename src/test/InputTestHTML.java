package test;

import static org.junit.Assert.*;

import java.io.File;

import org.jsoup.select.Selector.SelectorParseException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import data.Salesman;
import input.HTMLInput;

public class InputTestHTML {

    File testFile = new File("src/test_input_files/test-case-3-HTML.html");
    File nonExistantFile = new File("src/nonExistantFile.html");
	File MissingElementsHTML = new File("src/test_exception_cases/test-exception-case-1-HTML.html");
	
	
    HTMLInput inputFileHTML;
    HTMLInput inputFileHTML2;
	HTMLInput inputFileHTML3;
	
	
    Salesman salesman = new Salesman();

    @Before
    public void setUp() {
    	inputFileHTML = new HTMLInput(testFile);
    	inputFileHTML2 = new HTMLInput(nonExistantFile);
    	inputFileHTML3 = new HTMLInput(MissingElementsHTML);
	 	
    }
		         
    @Test
    public void testSuccessfulReadingAndParsing() throws SAXException {
    	inputFileHTML.readReceipts();
        salesman = inputFileHTML.getSalesman();

        assertEquals("Anastasios Zarras", salesman.getName());
        assertEquals("130456097", salesman.getAfm());
    }
       
    @Test
    public void testExceptionWhenFileDoesNotExist() {
        try {
        	inputFileHTML2.readReceipts();
        } catch (Exception exception) {
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().contains("Issues while creating DocumentBuilder"));
        }
    }
    
    @Test
    public void testSelectorParseException() {
        try {
            inputFileHTML3.readReceipts();
            salesman = inputFileHTML.getSalesman();
           
        } catch (SelectorParseException | SAXException  e) {
        	assertNotNull(e.getMessage());
            assertTrue(e.getMessage().contains("because of missing tags"));
        }
    }
    
   
   
}
