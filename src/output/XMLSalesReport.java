package output;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileOutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import data.Salesman;

public class XMLSalesReport extends SalesReport{

	private DocumentBuilder documentBuilder;
	private Document document;
	private Element salesmanElem;
	
	public XMLSalesReport(Salesman a,String path){
			salesman = a;
			this.file = new File(path);
	}	
	
	public void setUpSalesReportStructureForExtraction() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            salesmanElem = document.createElement("Salesman");
            document.appendChild(salesmanElem);
        } catch (ParserConfigurationException e) {
        	 e.printStackTrace();
        }
    }

	@Override
	public void writeSalesReportData(String dataName, String value) {
		if(dataName.equals("Name") || dataName.equals("AFM") || dataName.equals("Commission")) {
			Element ele = document.createElement(dataName);
	       	ele.appendChild(document.createTextNode(value));
	       	salesmanElem.appendChild(ele);
		}else if(dataName.equals("Sales")) {
			Element ele = document.createElement("Total"+dataName);
	       	ele.appendChild(document.createTextNode(value));
	       	salesmanElem.appendChild(ele);
		}else{
			Element ele = document.createElement(dataName+"Sales");
	       	ele.appendChild(document.createTextNode(value));
	       	salesmanElem.appendChild(ele);	
		}		
	}

	@Override
    public void finalizeExtraction() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMSource domSource = new DOMSource(document);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                StreamResult streamResult = new StreamResult(fos);
                transformer.transform(domSource, streamResult);
            }

        } catch (Exception ex) {
        	 ex.printStackTrace();
        }
    }
}

