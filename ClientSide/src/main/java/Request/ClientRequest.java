package Request;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ClientRequest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
            //FOR ALL MEASUREMENTS
        String url = "http://localhost:9999/measurements";
        String responseAllMeasurements = restTemplate.getForObject(url,String.class);

        System.out.println(responseAllMeasurements);

        //FOR RAINY DAYS
        String urlrainyDays = "http://localhost:9999/measurements/rainyDaysCount";
        String responserainyDays = restTemplate.getForObject(urlrainyDays,String.class);

        System.out.println(responserainyDays);

//        //FOR SENSOR REGISTRATION
//        Map<String, String> jsonSensorData = new HashMap<>();
//        jsonSensorData.put("name", "sensor8");
//
//        HttpEntity<Map<String,String>> requestsensor = new HttpEntity<>(jsonSensorData);
//
//        String urlforRequestSensorName = "http://localhost:9999/sensors/registration";
//        String responseInsertSensor = restTemplate.postForObject(urlforRequestSensorName,requestsensor, String.class);
//        System.out.println(responseInsertSensor);

        //FOR MEASUREMENTS DATA INSERTION
        Map<String, Object> jsonMeasurementsData = new HashMap<>();
        jsonMeasurementsData.put("value", 44.523);
        jsonMeasurementsData.put("raining", false);
        jsonMeasurementsData.put("sensor_id", Map.of("name","sensor8"));

        HttpEntity<Map<String,Object>> requestInsertMeasurements = new HttpEntity<>(jsonMeasurementsData);

        String urlforRequestMeasurementsData = "http://localhost:9999/measurements/add";
        String responseInsertMeasurementsData = restTemplate.postForObject(urlforRequestMeasurementsData,requestInsertMeasurements, String.class);
        System.out.println(responseInsertMeasurementsData);
//
//        ///////////////////
//        String responseAllMeasurementstwo = restTemplate.getForObject(url,String.class);
//
//        System.out.println(responseAllMeasurementstwo);

    }
}
