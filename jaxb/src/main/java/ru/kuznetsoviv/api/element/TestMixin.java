package ru.kuznetsoviv.api.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestMixin {

    @XmlElement
    private String str;

    public TestMixin() {

    }

    public TestMixin(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
