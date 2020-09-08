package ru.kuznetsoviv.processing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ru.kuznetsoviv.api.CustomMixin;
import ru.kuznetsoviv.api.element.TestMixin;

public class XmlProcessing {

    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomMixin.class);
        CustomMixin customMixin = new CustomMixin("comment", "data", new TestMixin("rr"));

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(customMixin, System.out);

    }

}
