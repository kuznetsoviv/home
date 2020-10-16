package ru.kuznetsoviv.xpath;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xpath {

    public static void main(String[] args) throws Exception {
        Document document = createDocument();
        XPathExpression xPathExpression = createXpathExpression("/bdn:Tutorials/bdn:Tutorial");
        NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
        System.out.println("result");
    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element tutorials = document.createElementNS("full_archive", "Tutorials");
        tutorials.appendChild(createTutorial(document, "Guava", "Introduction to Guava", "04/04/2016", "GuavaAuthor"));
        tutorials.appendChild(createTutorial(document, "XML", "Introduction to XPath", "04/04/2017", "XMLAuthor"));
        document.appendChild(tutorials);
        return document;
    }

    private static Element createTutorial(Document document, String title, String description, String date, String author) {
        Element tutorial = document.createElementNS("full_archive", "Tutorial");
        Element titleElement = document.createElement("title");
        titleElement.setTextContent(title);
        tutorial.appendChild(titleElement);
        Element descriptionElement = document.createElement("description");
        descriptionElement.setTextContent(description);
        tutorial.appendChild(descriptionElement);
        Element dateElement = document.createElement("date");
        dateElement.setTextContent(date);
        tutorial.appendChild(dateElement);
        Element authorElement = document.createElement("author");
        authorElement.setTextContent(author);
        tutorial.appendChild(authorElement);
        return tutorial;
    }

    private static XPathExpression createXpathExpression(String expression) throws XPathExpressionException {
        XPathFactory xpathFact = XPathFactory.newInstance();
        XPath xpath = xpathFact.newXPath();
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public String getNamespaceURI(String prefix) {
                if ("bdn".equals(prefix)) {
                    return "full_archive";
                }
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public Iterator getPrefixes(String namespaceURI) {
                return null;
            }
        });
        return xpath.compile(expression);
    }

    private static String parseDocument(Document document) throws TransformerException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(bos));
        return new String(bos.toByteArray());
    }

    private static Document parseResource(InputStream resourceStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        return builder.parse(resourceStream);
    }

}

