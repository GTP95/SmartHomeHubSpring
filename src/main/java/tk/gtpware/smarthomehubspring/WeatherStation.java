package tk.gtpware.smarthomehubspring;

import static java.lang.Math.exp;
import static java.lang.Math.log;

public class WeatherStation {
    private static WeatherStation myInstance=null;
    private float humidity, temperature, heatIndex;
    double dewPoint;
    String lastUpdateTimestamp, howAirFeels;

    private WeatherStation() {
    }

    public static WeatherStation getInstance(){
        if(myInstance==null){
            myInstance=new WeatherStation();
            return myInstance;
        }
        return myInstance;
    }

    public void receiveData(WeatherStationData data){
        humidity=data.humidity;
        temperature=data.temperature;
        heatIndex=data.heatIndex;
        dewPoint=dewPoint(humidity, temperature);
        lastUpdateTimestamp=data.lastUpdateTimestamp;
        howAirFeels=howAirFeels(dewPoint);
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHeatIndex() {
        return heatIndex;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public String getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public String getHowAirFeels() {
        return howAirFeels;
    }

    private double dewPoint(float humidity, float temperature){  //formula explanation: https://en.wikipedia.org/wiki/Dew_point
        double a, b ,c, d, dewPoint;
        d = 234.5;
        //switch/case can't operate on float...
        if(temperature>=0 && temperature<=50){
            a=6.1121;
            b=17.368;
            c=238.88;
        }
        else if(temperature>=-40 && temperature <0){
            a = 6.1121;
            b = 17.966;
            c = 247.15;
        }
        else{   //These valuations provide a maximum error of 0.1%, for −30 °C ≤ T ≤ 35°C and 1% < RH < 100%
            a = 6.112;
            b = 17.62;
            c = 243.12;
        }

        double psm=a*exp(b-temperature/d);
        double gamma=log(humidity/100*exp((b-temperature/d)*(temperature/(c+temperature))));
        dewPoint=c*gamma/(b-gamma);

        return dewPoint;
    }

    private String howAirFeels(double dewPoint){ //https://www.best-microcontroller-projects.com/dht22.html
        if(dewPoint<=13) return "secca";
        if(dewPoint>13 && dewPoint<=16) return "confortevole";
        if(dewPoint>16 && dewPoint<=18) return "abbastanza umida";
        if(dewPoint>18 && dewPoint<=21) return "umida";
        if(dewPoint>21 && dewPoint<=24) return "molto umida";
        else return "troppo umida";     //dewPoint>24
    }

}
