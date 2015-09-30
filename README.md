[![Build Status](https://travis-ci.org/chrismaroud/ns-api.svg?branch=master)](https://travis-ci.org/chrismaroud/ns-api)
![](https://upload.wikimedia.org/wikipedia/en/d/dd/Nederlandse_spoorwegen_logo.svg)
# What is the NS API?
The NS (Dutch Railways) provides a set of [public REST services](http://www.ns.nl/api/api) for non-commercial use by outside developers. This project provides a simple-to-use Java implementation and doesn't depend on any third-party libraries.

# Getting started

### Step 1. Request access to the public NS REST services
Submit the API access request form to get access to the NS REST API services:  [https://www.ns.nl/ews-aanvraagformulier/](https://www.ns.nl/ews-aanvraagformulier/). 

An username and password will be emailed to you immediately after.

### Step 2. Add ns-api to your project
Gradle:
    'com.bitsfromspace:ns-api:1.0-SNAPSHOT'

Maven:

<dependency>
  <groupId>com.bitsfromspace</groupId>
  <artifactId>ns-api</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>

Or download the latest release here: 

### Step 3. Use the API
```java
NsApi api = new NsApiImpl("username", "password");

//print out stations

for (Station station : api.getStations()){
    System.out.printf("Station ID %s, Name: %s, GPS location: %s\n", 
                      station.getId(),
                      station.getName(), 
                      station.getLocation()); 
}


// get departure info

for (Departure departure : api.getDepartures("ut")){
    if (departure.isDelayed()){
        System.out.printf("Train to %s scheduled at %tH:%tM is delayed by %d minutes.", 
                          departure.getFinalDestination(),
                          departure.getDepartureTime(),
                          departure.getDepartureTime(),
                          departure.getDelayInMinutes());
    }
    if (departure.isCancelled()){
        System.out.printf("Train to %s scheduled at %tH:%tM has been cancelled.", 
                          departure.getFinalDestination(),
                          departure.getDepartureTime(),
                          departure.getDepartureTime());
    }
}
//get interruptions

Interruptions interruptions = api.getInterruptions()
            .includeCurrentInterruptions(true)
            .includePlannedInterruptions(false)
            .stationId("htnc")
            .getInterruptions();

for (Interruption scheduledInterruption : interruptions.getScheduledInterruptions()){
    System.out.printf("Scheduled interruption: %s: %s", 
                      scheduledInterruption.getRoute(), 
                      scheduledInterruption.getAdvice());
}
for (Interruption unscheduledInterruption : interruptions.getUnscheduledInterruptions()){
    System.out.printf("Unscheduled interruption: %s: %s", 
                      unscheduledInterruption.getRoute(), 
                      unscheduledInterruption.getReason());
}

```
# Continue reading
Bugs and feature request can be logged [here](/chrismaroud/ns-api/issues)
