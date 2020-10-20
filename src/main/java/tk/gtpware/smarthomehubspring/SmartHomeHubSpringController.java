package tk.gtpware.smarthomehubspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SmartHomeHubSpringController {

    @RequestMapping(value="/WeatherStation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody void receiveWeatherStationData(@RequestBody WeatherStationData data){
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