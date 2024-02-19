package test;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import data.Salesman;
import input.TXTInput;

public class InputTestTXT {
	
	File testFile = new File("src/test_input_files/test-case-1-TXT.txt");
	File nonExistantFile = new File("src/nonExistantFile.txt");
	File InvalidFormatType = new File("src/test_exception_cases/test-exception-case-1-TXT.txt");
	Salesman salesman = new Salesman();
    TXTInput inputFileTXT;
    TXTInput inputFileTXT2;
    TXTInput inputFileTXT3;
    
    @Before
    public void setUp() {
        inputFileTXT = new TXTInput(testFile);
        inputFileTXT2= new TXTInput(nonExistantFile);
        inputFileTXT3= new TXTInput(InvalidFormatType);
    }

    @Test
    public void testSuccessfulReadingAndParsing() throws SAXException {
        inputFileTXT.readReceipts();
        salesman = inputFileTXT.getSalesman();

        assertEquals("Apostolos Zarras", salesman.getName());
        assertEquals("130456093", salesman.getAfm());
    }
       
    @Test
    public void testExceptionWhenFileDoesNotExist() {
        try {
        	inputFileTXT2.readReceipts();
        } catch (Exception exception) {
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().contains("Error setting up receipt structure for reading"));
        }
    }

    @Test
    public void testNumberFormatExceptionForInvalidReceiptIDType() {
        try {          
        	inputFileTXT3.readReceipts();   
            
        } catch (NumberFormatException | SAXException e) {
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().contains("invalid receipt ID type"));
        }        
    } 
}
