package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement(name = "Storing")
public class Interruption {
    private String id;
    private String route;
    private String reasonDescription;
    private String message;
    private Date date;
    private String periodDescription;
    private String adviceDescription;

    @XmlElement(name = "Datum")
    public void setDateIso8601(String date) {
        this.date = Iso8601Parser.parseDate(date);
    }

    public String getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    @XmlElement(name = "Traject")
    public void setRoute(String route) {
        this.route = route;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    @XmlElement(name = "Reden")
    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "Bericht")
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    @XmlElement(name = "Periode")
    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public String getAdviceDescription() {
        return adviceDescription;
    }

    @XmlElement(name = "Advies")
    public void setAdviceDescription(String adviceDescription) {
        this.adviceDescription = adviceDescription;
    }
}
