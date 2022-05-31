package com.company;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class XMLParser {

    public void XMLParse(String filename,Table table){
        ArrayList<Row> rows = table.getRows();
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Table element
            Element rootElement = doc.createElement("table");
            // table attribute
            Attr tableName = doc.createAttribute("name");
            tableName.setValue(table.getTableName()); //gets name of the table and adds it as attribute
            rootElement.setAttributeNode(tableName);
            doc.appendChild(rootElement);

            // Row element
                for(Row r: rows){
                    r.values.values();//za vseki red stoinosti
                    Element row = doc.createElement("row");
                    //row attribute
                    Attr rowId = doc.createAttribute("id");
                    rowId.setValue(String.valueOf(rows.indexOf(r)));
                    row.setAttributeNode(rowId);
                    rootElement.appendChild(row);
                    for(Map.Entry<Column, Object> c:r.values.entrySet())
                    {
                        Element column = doc.createElement("column");
                        Attr columnName = doc.createAttribute("name");
                        columnName.setValue(c.getKey().getColumnName());
                        column.setAttributeNode(columnName);
                        column.appendChild(doc.createTextNode((String) c.getValue()));
                        row.appendChild(column);
                    }
                }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename+".xml"));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

