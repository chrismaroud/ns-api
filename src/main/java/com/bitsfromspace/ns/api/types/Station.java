package com.bitsfromspace.ns.api.types;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author chris.oudeman
 * @since 16-Sep-15
 */
@XmlType(name = "Station")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Station {

    private String id;
    private String type;
    private String shortName;
    private String name;
    private String longName;
    private String countryCode;
    private String UICCode;
    private GeoPoint location;
    private List<String> alternativeNames;
    private double latitude;
    private double longitude;

    public String getId() {
        return id;
    }

    @XmlElement(name = "Code")
    public void setId(String id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @XmlElement(name = "Land")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getAlternativeNames() {
        return alternativeNames;
    }

    @XmlElement(name = "Synoniem")
    @XmlElementWrapper(name = "Synoniemen")
    public void setAlternativeNames(List<String> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "Type")
    public void setType(String type) {
        this.type = type;
    }

    public String getUICCode() {
        return UICCode;
    }

    public void setUICCode(String UICCode) {
        this.UICCode = UICCode;
    }

    @XmlElement(name = "Lat")
    public void setLat(double latitude) {
        this.latitude = latitude;
        parseLocation();
    }

    @XmlElement(name = "Lon", type = Double.class)
    public void setLongitude(double longitude) {
        this.longitude = longitude;
        parseLocation();
    }

    private void parseLocation() {
        if (latitude != 0 && longitude != 0) {
            location = new GeoPoint(latitude, longitude);
        }
    }

    @XmlElement(name = "Namen")
    public void setNamesWrapper(NamesWrapper wrapper) {
        shortName = wrapper.shortName;
        name = wrapper.name;
        longName = wrapper.longName;
    }

    static class NamesWrapper {
        @XmlElement(name = "Kort")
        private String shortName;
        @XmlElement(name = "Middel")
        private String name;
        @XmlElement(name = "Lang")
        private String longName;

    }
}
