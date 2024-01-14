package Project3TZServer.ServerSide.Controllers;

import Project3TZServer.ServerSide.Models.Measurements;

import Project3TZServer.ServerSide.Servers.MeasurementServices;
import Project3TZServer.ServerSide.Servers.SensorServices;
import Project3TZServer.ServerSide.util.SensorAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final SensorServices sensorServices;
    private final MeasurementServices measurementServices;

    @Autowired
    public MeasurementController(SensorServices sensorServices, MeasurementServices measurementServices) {
        this.sensorServices = sensorServices;
        this.measurementServices = measurementServices;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> measurementsAdd(@RequestBody @Valid Measurements measurements,
                                                      BindingResult bindingResult){
        System.out.println(measurements.getSensor_id());
        System.out.println(sensorServices.checkName(measurements.getSensor_id().getName()));

        if(sensorServices.checkName(measurements.getSensor_id().getName())){
            if(bindingResult.hasErrors()){
            StringBuilder ErrorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                ErrorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";\n");
            }
            throw new SensorAlreadyExistsException(ErrorMessage.toString());
        }
            measurementServices.saveMeasurements(measurements);
            System.out.println(measurements);
        }



        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @GetMapping()
    public List<Map<String,String>> allMeasurements(){
        List<Map<String,String>> list = new ArrayList<>();
        try {
            for (Measurements measurements : measurementServices.measurementsList()) {
                Map<String,String> jsonMeasurementsData = new HashMap<>();

                jsonMeasurementsData.put("id", Integer.toString(measurements.getId()));
                jsonMeasurementsData.put("value", Float.toString(measurements.getValue()));
                jsonMeasurementsData.put("raining", Boolean.toString(measurements.isRaining()));
                jsonMeasurementsData.put("sensor_name", sensorServices.getNameById(measurements.getId()));
                jsonMeasurementsData.put("measuredTime", measurements.getMeasuredTime().toString());
                list.add(jsonMeasurementsData);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return list;
    }

    @GetMapping("/rainyDaysCount")
    public java.util.Map<String,Integer> rainDCounts(){
        Map<String, Integer> jsonRainyDaysCount = new HashMap<>();
        jsonRainyDaysCount.put("rainyDaysCount", measurementServices.raindyDayCounts());
        return jsonRainyDaysCount;
    }


}
