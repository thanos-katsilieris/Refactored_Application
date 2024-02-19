package output;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReceiptUpdaterXML  extends ReceiptUpdater{
	
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	private NodeList receiptsList;
	private Element receiptElem;
	
	@Override
    public void setUpReceiptStructureForUpdating() {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(receiptFileToAppend);

            receiptsList = doc.getElementsByTagName("Receipts");

            Node receipts = receiptsList.item(0);
            receiptElem = doc.createElement("Receipt");
            receipts.appendChild(receiptElem);

        } catch (SAXException | IOException | ParserConfigurationException e) {
        	e.printStackTrace();
        }
    }
	
	@Override
	public void appendNewReceiptData(String receiptData, String newValueToAppend){
					
		Element ElementOfReceipt = doc.createElement(receiptData);
		ElementOfReceipt.appendChild(doc.createTextNode(newValueToAppend));
		receiptElem.appendChild(ElementOfReceipt);		
	}
	
	 @Override
	    public void finalizeTransformationToFile()   {
	        try {
	            Source xslt = new StreamSource(new File("src//test_input_files//Styles.xsl"));
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer(xslt);
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(receiptFileToAppend);
	            transformer.transform(source, result);
	        } catch (TransformerException e) {
	             e.printStackTrace();
	        }
	    }
}
