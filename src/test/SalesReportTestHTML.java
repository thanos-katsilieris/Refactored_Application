package test;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.Before;
import org.junit.Test;
import data.Salesman;
import output.HTMLSalesReport;

public class SalesReportTestHTML {
    private Salesman salesman;
    private String outputPath = "src/testing_outputs/output.html";
    private HTMLSalesReport htmlSalesReport;

    @Before
    public void setUp() {
     
        salesman = new Salesman();
        salesman.setName("Vassileios Zarras");
        salesman.setAfm("130456097");

        htmlSalesReport = new HTMLSalesReport(salesman, outputPath);
    }

    @Test
    public void testInitAndTransform() throws IOException {
        htmlSalesReport.setUpSalesReportStructureForExtraction();
        htmlSalesReport.writeSalesReportData("Name", salesman.getName());
        htmlSalesReport.writeSalesReportData("AFM", salesman.getAfm());
        htmlSalesReport.writeSalesReportData("Sales","40000");
        htmlSalesReport.writeSalesReportData("Trousers","34.0");
        htmlSalesReport.writeSalesReportData("Skirts","44.0");
        htmlSalesReport.writeSalesReportData("Shirts","21.0");
        htmlSalesReport.writeSalesReportData("Coats","35.0");
        htmlSalesReport.writeSalesReportData("Commission","5000");
        htmlSalesReport.finalizeExtraction();

      
        File outputFile = new File(outputPath);
        assertEquals(true, outputFile.exists());

     
        String expectedContent = "<html>\n<head>\n<title>Salesman</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; color: #333; line-height: 1; }\n" +
                "        .container { width: 80%; margin: auto; overflow: hidden; }\n" +
                "        .salesman-info, .receipt { background: #fff; padding: 20px; margin-bottom: 20px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }\n" +
                "        .salesman-info h1, .receipt h3 { color: #333; }\n" +
                "        .receipt { background-color: #e7eff6; border-left: 5px solid #3e9ecf; }\n" +
                "    </style>\n" +
                "</head>\n<body>\n<div class=\"container\">\n" +
                "<div class=\"salesman-info\">\n" +
                "<p><strong>Name:</strong> Vassileios Zarras</p>\n" +
                "<p><strong>AFM:</strong> 130456097</p>\n" +
                "<div class=\"receipt\">\n" +
                "<p><strong>Sales:</strong> 40000</p>\n" +
                "<p><strong>Trousers:</strong> 34.0</p>\n" +
                "<p><strong>Skirts:</strong> 44.0</p>\n" +
                "<p><strong>Shirts:</strong> 21.0</p>\n" +
                "<p><strong>Coats:</strong> 35.0</p>\n" +
                "<p><strong>Commission:</strong> 5000</p>\n" +
                "</div>\n" +
                "</div>\n</body>\n</html>";

        String actualContent = Files.readString(outputFile.toPath());

        assertEquals(expectedContent, actualContent);
    }   
}