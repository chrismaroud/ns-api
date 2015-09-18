package com.bitsfromspace.ns.api;

import com.bitsfromspace.ns.api.io.HttpTransport;
import com.bitsfromspace.ns.api.rest.NsRestSessionImpl;
import com.bitsfromspace.ns.api.types.*;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
@SuppressWarnings("unchecked")
public class NsApiImplTest {

    private String apiBaseUrl;
    private HttpTransport httpTransportMock;
    private Credentials credentialsMock;
    private String expectedAuthHeader;

    @Before
    public void setup() {
        apiBaseUrl = "http://" + Math.random() + "/";
        credentialsMock = mock(Credentials.class);
        httpTransportMock = mock(HttpTransport.class);
        when(credentialsMock.getUserName()).thenReturn("ns-user");
        when(credentialsMock.getPassword()).thenReturn("ns-password");
        expectedAuthHeader = "Basic bnMtdXNlcjpucy1wYXNzd29yZA==";
    }


    @Test
    public void testGetStations() {
        NsApi nsApi = new NsApiImpl(new NsRestSessionImpl(httpTransportMock, credentialsMock, apiBaseUrl));


        when(httpTransportMock.get(anyString(), anyMap(), anyMap()))
                .thenReturn(asStream("/ns-api-stations-v2.xml"));

        List<Station> stations = nsApi.getStations();
        assertEquals(630, stations.size());

        assertEquals("HT", stations.get(0).getId());
        assertEquals("knooppuntIntercitystation", stations.get(0).getType());
        assertEquals("Den Bosch", stations.get(0).getShortName());
        assertEquals("'s-Hertogenbosch", stations.get(0).getName());
        assertEquals("'s-Hertogenbosch", stations.get(0).getLongName());
        assertEquals("NL", stations.get(0).getCountryCode());
        assertEquals("8400319", stations.get(0).getUICCode());
        assertEquals(new GeoPoint(51.69048, 5.29362), stations.get(0).getLocation());
        assertEquals(2, stations.get(0).getAlternativeNames().size());
        assertEquals("Hertogenbosch ('s)", stations.get(0).getAlternativeNames().get(0));
        assertEquals("Den Bosch", stations.get(0).getAlternativeNames().get(1));

        verify(httpTransportMock, times(1)).get(apiBaseUrl + "ns-api-stations-v2", EMPTY_MAP, singletonMap("Authorization", expectedAuthHeader));
        verifyNoMoreInteractions(httpTransportMock);

    }

    @Test
    public void testGetDepartures() {
        NsApi nsApi = new NsApiImpl(new NsRestSessionImpl(httpTransportMock, credentialsMock, apiBaseUrl));


        when(httpTransportMock.get(anyString(), anyMap(), anyMap()))
                .thenReturn(asStream("/ns-api-avt.xml"));

        List<Departure> departures = nsApi.getDepartures("UT");
        assertEquals(35, departures.size());

        assertEquals(970, departures.get(0).getRouteId());
        assertEquals("Breda", departures.get(0).getFinalDestination());
        assertEquals("Intercity direct", departures.get(0).getTrainType());
        assertEquals("Rotterdam C.", departures.get(0).getRouteDescription());
        assertEquals("NS", departures.get(0).getRailwayCompany());
        assertEquals("6", departures.get(0).getPlatformDescription());
        assertFalse(departures.get(0).isCancelled());
        assertFalse(departures.get(0).isDelayed());

        assertFalse(departures.get(20).isCancelled());
        assertTrue(departures.get(20).isDelayed());
        assertEquals(6, departures.get(20).getDelayInMinutes());
        assertEquals("+6 min", departures.get(20).getDelayDescription());

        assertEquals("1-2", departures.get(4).getPlatformDescription());

        assertFalse(departures.get(8).isDelayed());
        assertTrue(departures.get(8).isCancelled());
        assertEquals(1, departures.get(8).getNotes().size());
        assertEquals("Rijdt vandaag niet", departures.get(8).getNotes().get(0));

        verify(httpTransportMock, times(1)).get(apiBaseUrl + "ns-api-avt", singletonMap("station", "UT"), singletonMap("Authorization", expectedAuthHeader));
        verifyNoMoreInteractions(httpTransportMock);
    }

    @Test
    public void testGetPrices() {
        NsApi nsApi = new NsApiImpl(new NsRestSessionImpl(httpTransportMock, credentialsMock, apiBaseUrl));


        when(httpTransportMock.get(anyString(), anyMap(), anyMap()))
                .thenReturn(asStream("/ns-api-prijzen-v3.xml"));

        List<TravelOption> travelOptions = nsApi.getPrices("htnc", "ut").day("15092015").viaStationId("htn").getPrices();
        assertEquals(3, travelOptions.size());

        assertEquals("MEEST_NS", travelOptions.get(0).getDescription());
        assertEquals(82, travelOptions.get(0).getRateUnits());
        assertEquals(2, travelOptions.get(0).getTypeTickets().size());
        assertEquals("Retour", travelOptions.get(0).getTypeTickets().get(0).getTicketTypeDescription());
        assertEquals(2, travelOptions.get(0).getTypeTickets().get(0).getTickets().size());
        assertEquals(1, travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getTravelClass());
        assertEquals(47.20, travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getTotalPrice(), 0);
        assertEquals(1, travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getPriceParts().size());
        assertEquals("NS", travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getPriceParts().get(0).getRailwayCompanyName());
        assertEquals(47.20, travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getPriceParts().get(0).getPrice(), 0);
        assertEquals("RTD", travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getPriceParts().get(0).getFromStationId());
        assertEquals("GDM", travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getPriceParts().get(0).getToStationId());
        assertEquals(3, travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getDiscountOptions().size());
        assertEquals("vol tarief", travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getDiscountOptions().get(0).getDescription());
        assertEquals(47.20, travelOptions.get(0).getTypeTickets().get(0).getTickets().get(0).getDiscountOptions().get(0).getPrice(), 0);

        assertEquals(2, travelOptions.get(1).getTypeTickets().get(0).getTickets().get(0).getPriceParts().size());

        Map<String, String> expectedParams = new HashMap<>();
        expectedParams.put("from", "htnc");
        expectedParams.put("to", "ut");
        expectedParams.put("via", "htn");
        expectedParams.put("dateTime", "15092015");

        verify(httpTransportMock, times(1)).get(apiBaseUrl + "ns-api-prijzen-v3", expectedParams, singletonMap("Authorization", expectedAuthHeader));
        verifyNoMoreInteractions(httpTransportMock);
    }

    @Test
    public void testGetInterruptions() {
        NsApi nsApi = new NsApiImpl(new NsRestSessionImpl(httpTransportMock, credentialsMock, apiBaseUrl));


        when(httpTransportMock.get(anyString(), anyMap(), anyMap()))
                .thenReturn(asStream("/ns-api-storingen.xml"));

        Interruptions interruptions = nsApi.getInterruptions()
                .includeCurrentInterruptions(true)
                .includePlannedInterruptions(false)
                .stationId("htnc")
                .getInterruptions();

        assertNotNull(interruptions);
        assertEquals(1, interruptions.getUnscheduledInterruptions().size());
        assertEquals("prio-13345", interruptions.getUnscheduledInterruptions().get(0).getId());
        assertEquals("'s-Hertogenbosch-Nijmegen", interruptions.getUnscheduledInterruptions().get(0).getRoute());
        assertEquals("beperkingen op last van de politie", interruptions.getUnscheduledInterruptions().get(0).getReasonDescription());
        assertEquals(1292494560000L, interruptions.getUnscheduledInterruptions().get(0).getDate().getTime());

        assertEquals(1, interruptions.getScheduledInterruptions().size());
        assertEquals("2010_almo_wp_18_19dec", interruptions.getScheduledInterruptions().get(0).getId());
        assertEquals("Almere Oostvaarders-Weesp/Naarden-Bussum", interruptions.getScheduledInterruptions().get(0).getRoute());
        assertEquals("zaterdag 18 en zondag 19 december", interruptions.getScheduledInterruptions().get(0).getPeriodDescription());
        assertEquals("Beperkt treinverkeer, businzet en/of omreizen, extra reistijd 15-30 min.", interruptions.getScheduledInterruptions().get(0).getReasonDescription());
        assertEquals("Maak gebruik van de overige treinen of de bussen: reis tussen Weesp en Almere Centrum met de NS-bus\n" +
                        "                in plaats van de trein tussen Almere Centrum en Lelystad Centrum rijden vier Sprinters per uur reis\n" +
                        "                tussen Almere Muziekwijk en Naarden-Bussum via Weesp\n" +
                        "            ",
                interruptions.getScheduledInterruptions().get(0).getAdviceDescription());


        Map<String, String> expectedParams = new HashMap<>();
        expectedParams.put("actual", "true");
        expectedParams.put("unplanned", "false");
        expectedParams.put("station", "htnc");


        verify(httpTransportMock, times(1)).get(apiBaseUrl + "ns-api-storingen", expectedParams, singletonMap("Authorization", expectedAuthHeader));
        verifyNoMoreInteractions(httpTransportMock);
    }

    private InputStream asStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }

}