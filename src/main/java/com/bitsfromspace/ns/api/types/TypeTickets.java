package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement(name = "ReisType")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TypeTickets {
    private String ticketTypeDescription;
    private List<Ticket> tickets;

    public String getTicketTypeDescription() {
        return ticketTypeDescription;
    }

    @XmlAttribute(name = "name")
    public void setTicketTypeDescription(String ticketTypeDescription) {
        this.ticketTypeDescription = ticketTypeDescription;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @XmlElement(name = "ReisKlasse")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
