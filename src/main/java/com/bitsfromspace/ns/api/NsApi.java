package com.bitsfromspace.ns.api;

import com.bitsfromspace.ns.api.types.Departure;
import com.bitsfromspace.ns.api.types.Interruptions;
import com.bitsfromspace.ns.api.types.Station;
import com.bitsfromspace.ns.api.types.TravelOption;

import java.util.Date;
import java.util.List;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public interface NsApi {

    List<Station> getStations();

    List<Departure> getDepartures(String stationId);

    GetPricesBuilder getPrices(String fromStationId, String toStationId);

    GetInterruptionsBuilder getInterruptions();

    interface GetPricesBuilder {
        GetPricesBuilder viaStationId(String viaStationId);

        GetPricesBuilder day(Date date);

        GetPricesBuilder day(String ddMMyyyy);

        List<TravelOption> getPrices();
    }

    interface GetInterruptionsBuilder {
        GetInterruptionsBuilder stationId(String stationId);

        GetInterruptionsBuilder includeCurrentInterruptions(boolean includeCurrentInterruptions);

        GetInterruptionsBuilder includePlannedInterruptions(boolean includePlannedInterruptions);

        Interruptions getInterruptions();
    }


}
