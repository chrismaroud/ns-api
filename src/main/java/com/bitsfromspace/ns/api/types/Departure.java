package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Departure {

    private int routeId;
    private Date departureTime;
    private int delayInMinutes;
    private String delayDescription;
    private String finalDestination;
    private String trainType;
    private String routeDescription;
    private String railwayCompany;
    private String platformDescription; // not-normalized, expected values include strings like: "5-6"
    private List<String> notes;

    public boolean isCancelled() {
        if (notes == null || notes.isEmpty()) {
            return false;
        }
        for (String note : notes) {
            if (note != null && note.toLowerCase().contains("rijdt vandaag niet")) {
                return true;
            }
        }
        return false;
    }

    public boolean isDelayed() {
        return delayInMinutes != 0;
    }

    public int getRouteId() {
        return routeId;
    }

    @XmlElement(name = "RitNummer")
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public int getDelayInMinutes() {
        return delayInMinutes;
    }

    public String getDelayDescription() {
        return delayDescription;
    }

    @XmlElement(name = "VertrekVertragingTekst")
    public void setDelayDescription(String delayDescription) {
        this.delayDescription = delayDescription;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    @XmlElement(name = "EindBestemming")
    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    public String getTrainType() {
        return trainType;
    }

    @XmlElement(name = "TreinSoort")
    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    @XmlElement(name = "RouteTekst")
    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    public String getRailwayCompany() {
        return railwayCompany;
    }

    @XmlElement(name = "Vervoerder")
    public void setRailwayCompany(String railwayCompany) {
        this.railwayCompany = railwayCompany;
    }

    public String getPlatformDescription() {
        return platformDescription;
    }

    @XmlElement(name = "VertrekSpoor")
    public void setPlatformDescription(String platformDescription) {
        this.platformDescription = platformDescription;
    }

    public List<String> getNotes() {
        return notes;
    }

    @XmlElementWrapper(name = "Opmerkingen")
    @XmlElement(name = "Opmerking")
    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    @XmlElement(name = "VertrekTijd")
    public void setDepartureTimeIso8601(String departureTimeString) {
        this.departureTime = Iso8601Parser.parseDate(departureTimeString);
    }

    @XmlElement(name = "VertrekVertraging")
    public void setDelayInMinutesIso8601(String delayInMinutesIso8601) {
        this.delayInMinutes = Iso8601Parser.parsePeriodMinutes(delayInMinutesIso8601);
    }

}