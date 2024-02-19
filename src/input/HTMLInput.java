package input;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector.SelectorParseException;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class HTMLInput extends Input {

    private Document doc;
    private List<String> receiptList = new ArrayList<>();
    int listElem = 0;

    public HTMLInput(File htmlFile) {
        inputFile = htmlFile;
    }

    @Override
    public void setUpReceiptStructureForReading() throws Exception {
        try {
            doc = Jsoup.parse(inputFile, "UTF-8", "");
            Elements element = doc.select("p,h3,td");
            receiptList = element.eachText();
        } catch (IOException e) {
             
            throw new Exception("Error parsing HTML file or HTML file doesn't exist " + e.getMessage(), e);
        } catch (SelectorParseException e ) {
        	 throw new SelectorParseException("Error parsing HTML file because of missing tags  " + e.getMessage(), e);
        }
        
    }

    @Override
    public String readData(String dataName){
        String value = null;
        
        value = receiptList.get(listElem).substring(receiptList.get(listElem).indexOf(":") + 1).trim();
        listElem++;
       
        return value;
    }

    @Override
    protected void readUnusedLines() throws IOException {
         
    }

    @Override
    protected int checkIfLoopFinished() {
        if (listElem == receiptList.size()) {
            return 1;
        } else {
            return 0;
        }
    }
}
