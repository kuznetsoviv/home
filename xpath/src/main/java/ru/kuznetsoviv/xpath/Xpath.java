package ru.kuznetsoviv.xpath;


import org.w3c.dom.Document;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.util.Iterator;

public class Xpath {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(Xpath.class.getResourceAsStream("/Tutorials.xml"));
        String xpathStr =
                "//a:itinerary/a:departure/b:departing";
        XPathFactory xpathFact = XPathFactory.newInstance();
        XPath xpath = xpathFact.newXPath();
        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public String getNamespaceURI(String prefix) {
                if ("a".equals(prefix)) {
                    return "http://travelcompany.example.org/reservation/travel";
                } else if ("b".equals(prefix)) {
                    return "http:namespace";
                } else
                    return "?";
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

        String result = xpath.evaluate(xpathStr, xmlDocument);
        System.out.println("result:" + result);
    }
        /*
        XPath xPath = XPathFactory.newInstance().newXPath();
        xPath.setNamespaceContext(new NamespaceContext() {
            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }
            @Override
            public String getPrefix(String arg0) {
                return null;
            }
            @Override
            public String getNamespaceURI(String arg0) {
                if ("bdn".equals(arg0)) {
                    return "/full_archive";
                }
                return null;
            }
        });
        String expression = "/bdn:Tutorials/bdn:Tutorial";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        System.out.println("test");

         */

}

