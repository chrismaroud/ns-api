package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement(name = "Kortingsprijs")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DiscountOption {
    private String description;
    private double price;

    public String getDescription() {
        return description;
    }

    @XmlAttribute(name = "name")
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    @XmlAttribute(name = "prijs")
    public void setPrice(double price) {
        this.price = price;
    }
}
