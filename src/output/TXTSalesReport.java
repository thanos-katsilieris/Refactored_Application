package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.Salesman;

public class TXTSalesReport extends SalesReport {

    private FileWriter txtWriter = null;

    public TXTSalesReport(Salesman a, String path) {
        salesman = a;
        this.file = new File(path);
    }

    @Override
    public void setUpSalesReportStructureForExtraction()  {
        try {
            txtWriter = new FileWriter(file);
        } catch (IOException e) {
        	 e.printStackTrace();
        }
    }

    public void writeSalesReportData(String dataName, String value) {
        try {
            if (dataName.equals("Name") || dataName.equals("AFM") || dataName.equals("Commission")) {
                txtWriter.write(dataName + ": " + value + "\n");
            } else if (dataName.equals("Sales")) {
                txtWriter.write("Total " + dataName + ": " + value + "\n");
            } else {
                txtWriter.write(dataName + " Sales: " + value + "\n");
            }
        } catch (IOException e) {
        	 e.printStackTrace();
        }
    }

    @Override
    public void finalizeExtraction() {
        try {
            txtWriter.close();
        } catch (IOException e) {
        	 e.printStackTrace();
        }
    }
}
