package output;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import data.Salesman;

public abstract class SalesReport {

    protected Salesman salesman;
    protected File file;

    public void extractSalesReport() throws IOException, ParserConfigurationException {

        try {
            setUpSalesReportStructureForExtraction();
            writeSalesReportToFile();
            finalizeExtraction();
        } catch (IOException e) {
           throw new IOException("Error during sales report extraction", e);
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Parser configuration error during sales report extraction");
        }
    }

    public abstract void setUpSalesReportStructureForExtraction() throws IOException, ParserConfigurationException;

    public abstract void writeSalesReportData(String dataName, String value) throws IOException;

    public void writeSalesReportToFile() throws IOException {

        try {
            writeSalesReportData("Name", salesman.getName());
            writeSalesReportData("AFM", salesman.getAfm());
            writeSalesReportData("Sales", Double.toString(salesman.calculateSalesData("Sales")));
            writeSalesReportData("Trousers", Double.toString(salesman.calculateSalesData("Trousers")));
            writeSalesReportData("Skirts", Double.toString(salesman.calculateSalesData("Skirts")));
            writeSalesReportData("Shirts", Double.toString(salesman.calculateSalesData("Shirts")));
            writeSalesReportData("Coats", Double.toString(salesman.calculateSalesData("Coats")));
            writeSalesReportData("Commission", Double.toString(salesman.calculateCommission()));
        } catch (IOException e) {           
            throw new IOException("Error writing sales report data to file", e);
        }
    }

    public abstract void finalizeExtraction() throws IOException;
}
