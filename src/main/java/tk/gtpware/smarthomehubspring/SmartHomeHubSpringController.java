package tk.gtpware.smarthomehubspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SmartHomeHubSpringController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/WeatherStation")
    public void receiveWeatherStationData(@RequestBody WeatherStationData data){
        WeatherStation.getInstance().receiveData(data);
    }

    @GetMapping("/WeatherStation")
    public String WeatherStation(Model model){
        WeatherStation weatherStation=WeatherStation.getInstance();
        model.addAttribute("humidity", weatherStation.getHumidity());
        model.addAttribute("temperature", weatherStation.getTemperature());
        model.addAttribute("heatIndex", weatherStation.getHeatIndex());
        model.addAttribute("dewPoint", weatherStation.getDewPoint());
        model.addAttribute("lastUpdateTimestamp", weatherStation.getLastUpdateTimestamp());
        model.addAttribute("howAirFeels", weatherStation.getHowAirFeels());
        return "WeatherStation";
    }

}