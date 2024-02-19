package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import data.Salesman;
import input.TXTInput;
import output.TXTSalesReport;

public class SalesReportTestTXT {
	
	File inputFile = new File("src/test_input_files/test-case-1-TXT.txt");
	Salesman salesman = new Salesman();
	String outputFile = "src/testing_outputs/output.txt";
	ArrayList <String> outputContents = new ArrayList<String>();
	ArrayList <String> saveContents = new ArrayList<String>();
	
	List<String> lines;
	
	@Test
	public void test() throws IOException, ParserConfigurationException, SAXException {
		setup();
		
		Path path = Paths.get(outputFile);
		lines = Files.readAllLines(path);
		assertEquals(outputContents,lines);
	}
	
	public void setup() throws IOException, ParserConfigurationException, SAXException {
		
		TXTInput inputFileTXT = new TXTInput(inputFile);
		
		inputFileTXT.readReceipts();
		salesman = inputFileTXT.getSalesman();

		TXTSalesReport testRepo = new TXTSalesReport(salesman,outputFile);
		testRepo.extractSalesReport();
		
		outputContents.add("Name: Apostolos Zarras");
		outputContents.add("AFM: 130456093");
		outputContents.add("Total Sales: 17000.0");
		outputContents.add("Trousers Sales: 10.0");
		outputContents.add("Skirts Sales: 20.0");
		outputContents.add("Shirts Sales: 10.0");
		outputContents.add("Coats Sales: 20.0");
		outputContents.add("Commission: 2050.0");
	}

}