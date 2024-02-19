package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import data.Receipt;
import output.ReceiptUpdaterXML;

public class ReceiptUpdaterXMLTest {
	File originalFile = new File("src/test_add_new_receipt/test_add_new_receipt_2_XML.xml");
	File temporaryCopy = new File("src/test_add_new_receipt/test_add_new_receipt_2_XMLcopied.xml");
	ArrayList <String> contents = new ArrayList<String>();
	
	
	@Test
	public void test() throws IOException, TransformerException, SAXException, ParserConfigurationException{

		 
			setup();
		 
		 try {
	            readTestfile();
	        } catch (IOException e) {
	            fail("IOException occurred while reading the test file: " + e.getMessage());
	        }
		
		assertEquals(true,checkcontents());
		
	}
	
	public void setup() throws IOException, TransformerException, SAXException, ParserConfigurationException {

			FileUtils.copyFile(originalFile, temporaryCopy);
		
			ReceiptUpdaterXML updater = new ReceiptUpdaterXML();
			Receipt receipt = new Receipt(150,"25/2/2014","Skirts",100,65,"Hand Made Clothes","Greece","Ioannina","Kaloudi",10);
			
			updater.setReceiptFileToAppend(temporaryCopy);
			updater.addNewReceipt(receipt);
		
	}
	
	public void readTestfile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(temporaryCopy));
		String line;
		
		while ((line = br.readLine()) != null) {
			contents.add(line.trim());
		}
		br.close();
	}
	
	public Boolean checkcontents() {
		Boolean found = false;
		for (int i=0;i<contents.size();i++) {
			if(contents.get(i).equals("<ReceiptID>150</ReceiptID>") 
			&& contents.get(i+1).equals("<Date>25/2/2014</Date>") && contents.get(i+2).equals("<Kind>Skirts</Kind>")
			&& contents.get(i+3).equals("<Sales>100.0</Sales>") && contents.get(i+4).equals("<Items>65</Items>")
			&& contents.get(i+5).equals("<Company>Hand Made Clothes</Company>") && contents.get(i+6).equals("<Country>Greece</Country>")
			&& contents.get(i+7).equals("<City>Ioannina</City>") && contents.get(i+8).equals("<Street>Kaloudi</Street>")
			&& contents.get(i+9).equals("<Number>10</Number>")) {
				found = true;
			}
		}
	
		return found;
	}
}
