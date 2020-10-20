package tk.gtpware.smarthomehubspring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherStationData {
    @JsonProperty("deviceID") String deviceID;    //useless for now, but might be useful in case I decide to have more stations
    @JsonProperty("temperature") float temperature;
    @JsonProperty("humidity") float humidity;
    @JsonProperty("heat index") float heatIndex;
    @JsonProperty("timestamp") String lastUpdateTimestamp;
}
