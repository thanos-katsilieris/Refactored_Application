package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptUpdaterTXT extends ReceiptUpdater {
    
    private BufferedWriter txtWriter;

    @Override
    protected void setUpReceiptStructureForUpdating() throws IOException {
        try {
            txtWriter = new BufferedWriter(new FileWriter(receiptFileToAppend, true));
        } catch (IOException e) {          
           e.printStackTrace();
        }
    }

    @Override
    public void appendNewReceiptData(String receiptData, String newValueToAppend) throws IOException {
        try {
            txtWriter.write("\n");
            txtWriter.write(receiptData + ": ");
            txtWriter.write(newValueToAppend);
        } catch (IOException e) {          
            e.printStackTrace();
        }
    }

    @Override
    protected void finalizeTransformationToFile() throws IOException {
        try {
            txtWriter.write("\n");
            txtWriter.close();
        } catch (IOException e) {         
            e.printStackTrace();
        }
    }
}
