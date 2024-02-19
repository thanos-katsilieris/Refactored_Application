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

import input.XMLInput;
import output.XMLSalesReport;

public class SalesReportTestXML {

	File inputFile = new File("src/test_input_files/test-case-2-XML.xml");
	Salesman salesman = new Salesman();
	String outputFile = "src/testing_outputs/output.xml";
	ArrayList <String> outputContents = new ArrayList<String>();
	XMLInput inputFileXML = new XMLInput(inputFile);
	List<String> lines;
	
	@Test
	public void test() throws IOException, ParserConfigurationException, SAXException {
		setup();
		
		Path path = Paths.get(outputFile);
		lines = Files.readAllLines(path);
		assertEquals(outputContents,lines);
	}
	
	public void setup() throws IOException, ParserConfigurationException, SAXException {
		inputFileXML.readReceipts();
		salesman = inputFileXML.getSalesman();
		
		XMLSalesReport testRepo = new XMLSalesReport(salesman,outputFile);
		testRepo.extractSalesReport();
		
		outputContents.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
		outputContents.add("<Salesman>");
		outputContents.add("    <Name>Vassileios Zarras</Name>");
		outputContents.add("    <AFM>130456097</AFM>");
		outputContents.add("    <TotalSales>41000.0</TotalSales>");
		outputContents.add("    <TrousersSales>30.0</TrousersSales>");
		outputContents.add("    <SkirtsSales>40.0</SkirtsSales>");
		outputContents.add("    <ShirtsSales>20.0</ShirtsSales>");
		outputContents.add("    <CoatsSales>40.0</CoatsSales>");
		outputContents.add("    <Commission>5700.0</Commission>");
		outputContents.add("</Salesman>");
	}

}
