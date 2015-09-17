package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

@XmlRootElement(name = "VervoerderKeuzes")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TravelOptions {
    private List<TravelOption> travelOptions;

    public List<TravelOption> getTravelOptions() {
        return travelOptions;
    }

    @XmlElement(name = "VervoerderKeuze")
    public void setTravelOptions(List<TravelOption> travelOptions) {
        this.travelOptions = travelOptions;
    }
}
