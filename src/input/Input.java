package input;


import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.Salesman;
import data.Receipt;
public abstract class Input {
	
	protected Salesman salesman;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected int receiptID;
	protected String date;
	protected String kind;
	protected double sales;
	protected int items;
	protected String companyName;
	protected String companyCountry;
	protected String companyCity;
	protected String companyStreet;
	protected int companyStreetNumber;
	
	
	public Input() {
		salesman = new Salesman();
	}
	
	public final void readReceipts() throws SAXException {
		try {
			
			setUpReceiptStructureForReading();
			
			readSalesmanData();
			
			addSalesman();
			
			readUnusedLines();
		
			while(true){
				readReceiptData();
				
				addReceipt();
							
				if(checkIfLoopFinished() == 1) {
					break;
				}
			}			
        } catch (Exception generalException) {
            generalException.printStackTrace();
            System.err.println("An unexpected exception occurred during the receipt reading process: " + generalException.getMessage());
        }
	}
	
	
	protected abstract void setUpReceiptStructureForReading() throws  Exception;
	
	public abstract String readData(String dataName) throws ParserConfigurationException, 
															Exception, SAXException  ;
	
	protected abstract void readUnusedLines() throws IOException;
	
	protected abstract int checkIfLoopFinished();
	
	protected void readSalesmanData() throws Exception {
		try {
			name = readData("Name");
			afm = readData("AFM");
		 } catch (IOException ioException) {
	         throw new IOException("Error reading salesman data: " + ioException.getMessage(), ioException);
	     }
	}
	
	
	
	protected void readReceiptData() throws Exception{
		try {
			receiptID = Integer.parseInt(readData("ReceiptID"));
			date = readData("Date");
			kind = readData("Kind");
			sales = Double.parseDouble(readData("Sales"));
			items = Integer.parseInt(readData("Items"));
			companyName = readData("Company");
			companyCountry = readData("Country");
			companyCity = readData("City");
			companyStreet = readData("Street");
			companyStreetNumber = Integer.parseInt(readData("Number"));
		 } catch (NumberFormatException | IOException e) {
		        
			 throw new NumberFormatException("Error occured while reading receipt data because of invalid receipt ID type. The input is not a valid integer: " + e );
		 }
	}
	
	
	public void addSalesman() {
		salesman.setName(name);
		salesman.setAfm(afm);
		
	}
	
	public void addReceipt( ){
		Receipt receipt = new Receipt(receiptID, date, kind, sales, items, companyName, companyCountry, companyCity, companyStreet, companyStreetNumber);
		if(receipt.getKind().equals("No specific kind")) {
			JOptionPane.showMessageDialog(null,"Βρέθηκε μη έγκυρο όνομα είδους");
		}
		salesman.getReceipts().add(receipt);
	}
	
	public Salesman getSalesman() {
		return salesman;
	}	
}
