package output;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ReceiptUpdaterHTML extends ReceiptUpdater {

    Document doc;
    Element tableElement;
    Element newTableRow;

    @Override
    public void setUpReceiptStructureForUpdating() throws IOException {
        try {
            doc = Jsoup.parse(receiptFileToAppend, "UTF-8");
            tableElement = doc.select("table").first();

            if (tableElement == null) {
                tableElement = doc.appendElement("table");
            }

            newTableRow = tableElement.appendElement("tr");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    @Override
    public void appendNewReceiptData(String receiptData, String newValueToAppend) throws IOException {
        Element dataElement = newTableRow.appendElement("td");
        dataElement.text(newValueToAppend);
    }

    @Override
    public void finalizeTransformationToFile() throws IOException {
        try {
            Files.write(receiptFileToAppend.toPath(), doc.outerHtml().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}