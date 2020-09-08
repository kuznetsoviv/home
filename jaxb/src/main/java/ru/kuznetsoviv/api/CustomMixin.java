package ru.kuznetsoviv.api;

import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.kuznetsoviv.api.element.TestMixin;

@XmlRootElement(name = "customMixin")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomMixin {

    @XmlAttribute
    private String comment;
    @XmlAttribute
    private String data;
    @XmlElement
    private TestMixin mixin;

    public CustomMixin() {
    }

    public CustomMixin(String comment, String data, TestMixin mixin) {
        this.comment = comment;
        this.data = data;
        this.mixin = mixin;
    }

    public String getComment() {
        return comment;
    }

    public String getData() {
        return data;
    }

    public TestMixin getMixin() {
        return mixin;
    }
}
