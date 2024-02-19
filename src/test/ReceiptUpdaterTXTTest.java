package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

import data.Receipt;
import output.ReceiptUpdaterTXT;

public class ReceiptUpdaterTXTTest {

    File testFile = new File("src/test_add_new_receipt/test_add_new_receipt_1_TXT.txt");

    ArrayList<String> contents = new ArrayList<String>();

    @Test
    public void test() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        setup();

        try {
            readTestfile();
        } catch (IOException e) {
            fail("IOException occurred while reading the test file: " + e.getMessage());
        }

        assertTrue(checkContents());
    }

    public void setup() throws IOException {
        ReceiptUpdaterTXT updater = new ReceiptUpdaterTXT();
        Receipt receipt = new Receipt(150, "25/2/2014", "Skirts", 100, 65, "Hand Made Clothes", "Greece", "Ioannina",
                "Kaloudi", 10);

        updater.setReceiptFileToAppend(testFile);
        updater.addNewReceipt(receipt);
    }

    public void readTestfile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        }
    }

    public Boolean checkContents() {
        Boolean found = false;

        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).equals("ReceiptID: 150") && contents.get(i + 1).equals("Date: 25/2/2014")
                    && contents.get(i + 2).equals("Kind: Skirts") && contents.get(i + 3).equals("Sales: 100.0")
                    && contents.get(i + 4).equals("Items: 65") && contents.get(i + 5).equals("Company: Hand Made Clothes")
                    && contents.get(i + 6).equals("Country: Greece") && contents.get(i + 7).equals("City: Ioannina")
                    && contents.get(i + 8).equals("Street: Kaloudi") && contents.get(i + 9).equals("Number: 10")) {
                found = true;
            }
        }
        return found;
    }
}
