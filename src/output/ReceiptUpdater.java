package output;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import data.Receipt;

public abstract class ReceiptUpdater {

    protected File receiptFileToAppend;

    public void setReceiptFileToAppend(File receiptFileToAppend) {
        this.receiptFileToAppend = receiptFileToAppend;
    }

    public final void addNewReceipt(Receipt receipt) {
        try {        	
			setUpReceiptStructureForUpdating();

			appendReceiptToFile(receipt);

			finalizeTransformationToFile();
        
        } catch (IOException e) {
            System.out.println("Error while processing receipt: IO exception");
        
        } catch (TransformerException | SAXException | ParserConfigurationException e) {
        	e.printStackTrace();
        }
    }

    protected abstract void setUpReceiptStructureForUpdating() throws IOException, SAXException, ParserConfigurationException;

    protected abstract void appendNewReceiptData(String receiptData, String newValueToAppend) throws IOException;

    protected abstract void finalizeTransformationToFile() throws IOException, TransformerException;

    protected void appendReceiptToFile(Receipt receipt) throws IOException {
        try {
            appendNewReceiptData("ReceiptID", Integer.toString(receipt.getReceiptID()));
            appendNewReceiptData("Date", receipt.getDate());
            appendNewReceiptData("Kind", receipt.getKind());
            appendNewReceiptData("Sales", Double.toString(receipt.getSales()));
            appendNewReceiptData("Items", Integer.toString(receipt.getItems()));
            appendNewReceiptData("Company", receipt.getCompany().getName());
            appendNewReceiptData("Country", receipt.getCompany().getCompanyAddress().getCountry());
            appendNewReceiptData("City", receipt.getCompany().getCompanyAddress().getCity());
            appendNewReceiptData("Street", receipt.getCompany().getCompanyAddress().getStreet());
            appendNewReceiptData("Number", Integer.toString(receipt.getCompany().getCompanyAddress().getStreetNumber()));
        } catch (IOException e) {
            throw new IOException("Error while appending receipt data to file: IO exception", e);
        }
    }
}