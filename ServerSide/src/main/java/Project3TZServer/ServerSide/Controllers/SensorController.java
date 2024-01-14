package Project3TZServer.ServerSide.Controllers;

import Project3TZServer.ServerSide.Models.Measurements;
import Project3TZServer.ServerSide.Models.Sensor;
import Project3TZServer.ServerSide.Servers.MeasurementServices;
import Project3TZServer.ServerSide.Servers.SensorServices;
import Project3TZServer.ServerSide.util.SensorAlreadyExistsException;
import Project3TZServer.ServerSide.util.SensorErrorResponse;
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
@RequestMapping("/sensors")
public class SensorController {

    private final SensorServices sensorServices;
    private final MeasurementServices measurementServices;

    @Autowired
    public SensorController(SensorServices sensorServices,
                            MeasurementServices measurementServices) {
        this.sensorServices = sensorServices;
        this.measurementServices = measurementServices;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorReg(@RequestBody @Valid Sensor sensor,
                                                BindingResult bindingResult){
        if(sensorServices.checkName(sensor.getName())){
            String sensorErrorMessage = "Error : Sensor with this name already exists!";

            throw new SensorAlreadyExistsException(sensorErrorMessage);
        }
        if(bindingResult.hasErrors()){
            StringBuilder ErrorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                ErrorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";\n");
            }
            throw new SensorAlreadyExistsException(ErrorMessage.toString());
        }
        sensorServices.InsertSensor(sensor);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorAlreadyExistsException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


}
