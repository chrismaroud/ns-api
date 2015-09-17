package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement(name = "ReisKlasse")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ticket {
    private int travelClass;
    private double totalPrice;
    private List<PricePart> priceParts;
    private List<DiscountOption> discountOptions;

    public int getTravelClass() {
        return travelClass;
    }

    @XmlAttribute(name = "klasse")
    public void setTravelClass(int travelClass) {
        this.travelClass = travelClass;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @XmlElement(name = "Totaal")
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PricePart> getPriceParts() {
        return priceParts;
    }

    @XmlElement(name = "Prijsdeel")
    public void setPriceParts(List<PricePart> priceParts) {
        this.priceParts = priceParts;
    }

    public List<DiscountOption> getDiscountOptions() {
        return discountOptions;
    }

    @XmlElement(name = "Kortingsprijs")
    @XmlElementWrapper(name = "Korting")
    public void setDiscountOptions(List<DiscountOption> discountOptions) {
        this.discountOptions = discountOptions;
    }
}
