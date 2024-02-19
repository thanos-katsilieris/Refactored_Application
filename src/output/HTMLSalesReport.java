package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.Salesman;

public class HTMLSalesReport extends SalesReport {

    FileWriter writer = null;
    boolean salesmanInfoInitialized = false;
    boolean receiptInitialized = false;

    public HTMLSalesReport(Salesman a, String path){        
            salesman = a;
            this.file = new File(path);        
    }

    @Override
    public void setUpSalesReportStructureForExtraction() throws IOException {
        try {
            writer = new FileWriter(file);

            writer.write("<html>\n<head>\n<title>Salesman</title>\n");
            writer.write("    <style>\n");

            writer.write("        body { font-family: Arial, sans-serif; background-color: #f4f4f4; color: #333; line-height: 1; }\n");
            writer.write("        .container { width: 80%; margin: auto; overflow: hidden; }\n");
            writer.write("        .salesman-info, .receipt { background: #fff; padding: 20px; margin-bottom: 20px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }\n");
            writer.write("        .salesman-info h1, .receipt h3 { color: #333; }\n");
            writer.write("        .receipt { background-color: #e7eff6; border-left: 5px solid #3e9ecf; }\n");
            writer.write("    </style>\n</head>\n<body>\n<div class=\"container\">\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeSalesReportData(String dataName, String value)  {
        try {
            if (!salesmanInfoInitialized && (dataName.equals("Name") || dataName.equals("AFM"))) {
                writer.write("<div class=\"salesman-info\">\n");
                salesmanInfoInitialized = true;
            }
            if (!receiptInitialized && !dataName.equals("Name") && !dataName.equals("AFM")) {
                writer.write("<div class=\"receipt\">\n");
                receiptInitialized = true;
            }

            writer.write("<p><strong>" + dataName + ":</strong> " + value + "</p>\n");
        } catch (IOException e) {
        	 e.printStackTrace();
        }
    }

    @Override
    public void finalizeExtraction() {
        try {
            writer.write("</div>\n</div>\n</body>\n</html>");
            writer.close();
        } catch (IOException e) {
        	 e.printStackTrace();
        }
    }
}
