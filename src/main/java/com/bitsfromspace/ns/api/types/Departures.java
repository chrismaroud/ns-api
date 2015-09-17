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
@XmlRootElement(name = "ActueleVertrekTijden")
@XmlAccessorType(XmlAccessType.FIELD)
public class Departures {

    @XmlElement(name = "VertrekkendeTrein")
    private List<Departure> departures;

    public List<Departure> getDepartures() {
        return departures;
    }
}
