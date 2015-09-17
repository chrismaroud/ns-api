package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement(name = "Prijsdeel")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PricePart {
    private String railwayCompanyName;
    private String fromStationId;
    private String toStationId;
    private double price;

    public String getRailwayCompanyName() {
        return railwayCompanyName;
    }

    @XmlAttribute(name = "vervoerder")
    public void setRailwayCompanyName(String railwayCompanyName) {
        this.railwayCompanyName = railwayCompanyName;
    }

    public String getFromStationId() {
        return fromStationId;
    }

    @XmlAttribute(name = "van")
    public void setFromStationId(String fromStationId) {
        this.fromStationId = fromStationId;
    }

    public String getToStationId() {
        return toStationId;
    }

    @XmlAttribute(name = "naar")
    public void setToStationId(String toStationId) {
        this.toStationId = toStationId;
    }

    public double getPrice() {
        return price;
    }

    @XmlAttribute(name = "prijs")
    public void setPrice(double price) {
        this.price = price;
    }
}
