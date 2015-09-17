package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TravelOption {

    private String description;
    private int rateUnits;
    private List<TypeTickets> typeTickets;

    public String getDescription() {
        return description;
    }

    @XmlAttribute(name = "naam")
    public void setDescription(String description) {
        this.description = description;
    }

    public int getRateUnits() {
        return rateUnits;
    }

    @XmlElement(name = "Tariefeenheden")
    public void setRateUnits(int rateUnits) {
        this.rateUnits = rateUnits;
    }

    public List<TypeTickets> getTypeTickets() {
        return typeTickets;
    }

    @XmlElement(name = "ReisType")
    public void setTypeTickets(List<TypeTickets> typeTickets) {
        this.typeTickets = typeTickets;
    }
}
