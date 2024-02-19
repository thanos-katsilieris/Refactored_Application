 package input;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TXTInput extends Input{
	
	private BufferedReader txtReader;

	public TXTInput(File recieptFileTXT){
		this.inputFile = recieptFileTXT;
		inputFilePath = inputFile.getAbsolutePath();
	}
	
    @Override
    protected void setUpReceiptStructureForReading() throws IOException {
        try {
            txtReader = new BufferedReader(new FileReader(inputFilePath));
        } catch (IOException e) {
            throw new IOException("Error setting up receipt structure for reading: " + e.getMessage(), e);
        }
    }

    @Override
    public String readData(String dataName) throws IOException {
        String value = null;

        try {
            dataName = txtReader.readLine();
        } catch (IOException e) {
            throw new IOException("Error reading data: " + e.getMessage(), e);
        }
        value = dataName.substring(dataName.indexOf(":") + 1).trim();
        return value;
    }

    @Override
    protected void readUnusedLines() throws IOException {      
            readData("\n");
            readData("Receipts");
            readData("\n");       
    }

    @Override
    protected int checkIfLoopFinished() {
        try {
            if (txtReader.readLine() == null) {
                return 1;
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}