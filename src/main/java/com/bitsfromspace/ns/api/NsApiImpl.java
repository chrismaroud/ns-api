package com.bitsfromspace.ns.api;

import com.bitsfromspace.ns.api.io.HttpTransportImpl;
import com.bitsfromspace.ns.api.rest.NsRestSession;
import com.bitsfromspace.ns.api.rest.NsRestSessionImpl;
import com.bitsfromspace.ns.api.rest.RestCall;
import com.bitsfromspace.ns.api.types.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public class NsApiImpl implements NsApi {

    private final NsRestSession nsRestSession;

    public NsApiImpl(Credentials credentials) {
        this(new NsRestSessionImpl(new HttpTransportImpl(), credentials));
    }

    public NsApiImpl(String apiUsername, String apiPassword) {
        this(new Credentials(apiUsername, apiPassword));
    }

    public NsApiImpl(NsRestSession nsRestSession) {
        this.nsRestSession = nsRestSession;
    }

    @Override
    public List<Station> getStations() {
        return nsRestSession
                .call(Stations.class, "ns-api-stations-v2")
                .execute()
                .getStations();
    }

    @Override
    public List<Departure> getDepartures(String stationId) {
        return nsRestSession
                .call(Departures.class, "ns-api-avt")
                .setParameter("station", stationId)
                .execute()
                .getDepartures();
    }

    @Override
    public GetPricesBuilder getPrices(String fromStationId, String toStationId) {
        final RestCall<TravelOptions> restCall = nsRestSession.call(TravelOptions.class, "ns-api-prijzen-v3");
        restCall.setParameter("from", fromStationId);
        restCall.setParameter("to", toStationId);
        return new GetPricesBuilder() {
            private final DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

            @Override
            public GetPricesBuilder viaStationId(String viaStationId) {
                restCall.setParameter("via", viaStationId);
                return this;
            }

            @Override
            public GetPricesBuilder day(Date date) {
                return day(dateFormat.format(date));
            }

            @Override
            public GetPricesBuilder day(String ddMMyyyy) {
                restCall.setParameter("dateTime", ddMMyyyy);
                return this;
            }

            @Override
            public List<TravelOption> getPrices() {
                return restCall.execute().getTravelOptions();
            }
        };
    }

    @Override
    public GetInterruptionsBuilder getInterruptions() {
        final RestCall<Interruptions> restCall = nsRestSession.call(Interruptions.class, "ns-api-storingen");
        return new GetInterruptionsBuilder() {
            @Override
            public GetInterruptionsBuilder stationId(String stationId) {
                restCall.setParameter("station", stationId);
                return this;
            }

            @Override
            public GetInterruptionsBuilder includeCurrentInterruptions(boolean includeCurrentInterruptions) {
                restCall.setParameter("actual", includeCurrentInterruptions);
                return this;
            }

            @Override
            public GetInterruptionsBuilder includePlannedInterruptions(boolean includePlannedInterruptions) {
                restCall.setParameter("unplanned", includePlannedInterruptions);
                return this;
            }

            @Override
            public Interruptions getInterruptions() {
                return restCall.execute();
            }
        };

    }
}
