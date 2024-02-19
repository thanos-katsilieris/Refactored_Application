package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import output.ReceiptUpdaterHTML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReceiptUpdaterHTMLTest {

	private ReceiptUpdaterHTML receiptUpdaterHTML;
    private File existingHtmlFile;

    @Before
    public void setUp() {
        String path = "src/test_add_new_receipt/test_add_new_receipt_3_HTML.html";
        existingHtmlFile = new File(path);

        receiptUpdaterHTML = new ReceiptUpdaterHTML();
        receiptUpdaterHTML.setReceiptFileToAppend(existingHtmlFile);

        try {
			receiptUpdaterHTML.setUpReceiptStructureForUpdating();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testAddNewReceipt() throws IOException {
        receiptUpdaterHTML.appendNewReceiptData("ReceiptID", "2333");
        receiptUpdaterHTML.appendNewReceiptData("Date", "25/2/2014");
        receiptUpdaterHTML.appendNewReceiptData("Kind", "Coats");
        receiptUpdaterHTML.appendNewReceiptData("Sales", "4000");
        receiptUpdaterHTML.appendNewReceiptData("Items", "10");
        receiptUpdaterHTML.appendNewReceiptData("Company", "Hand Made Clothes");
        receiptUpdaterHTML.appendNewReceiptData("Country", "Greece");
        receiptUpdaterHTML.appendNewReceiptData("City", "Ioannina");
        receiptUpdaterHTML.appendNewReceiptData("Street", "Kaloudi");
        receiptUpdaterHTML.appendNewReceiptData("Number", "10");

        receiptUpdaterHTML.finalizeTransformationToFile();

        assertTrue(hasReceiptInHtmlFile(existingHtmlFile, "2333", "25/2/2014", "Coats", "4000", "10",
                                         "Hand Made Clothes", "Greece", "Ioannina", "Kaloudi", "10"));
    }

    private boolean hasReceiptInHtmlFile(File file, String... values) {
        try {
            String htmlContent = Files.readString(file.toPath());

            for (String value : values) {
                String tag = "<td>" + value + "</td>";
                if (!htmlContent.trim().contains(tag.trim())) {
                    System.out.println("Value not found: " + tag);
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
