package Project3TZServer.ServerSide.Servers;

import Project3TZServer.ServerSide.Models.Measurements;
import Project3TZServer.ServerSide.Models.Sensor;
import Project3TZServer.ServerSide.Repositories.MeasurementsRepositories;
import Project3TZServer.ServerSide.Repositories.SensorRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementServices {

    private final MeasurementsRepositories measurementsRepositories;
    private final SensorRepositories sensorRepositories;

    @Autowired
    public MeasurementServices(MeasurementsRepositories measurementsRepositories, SensorRepositories sensorRepositories) {
        this.measurementsRepositories = measurementsRepositories;
        this.sensorRepositories = sensorRepositories;
    }

    @Transactional
    public void saveMeasurements(Measurements measurements){

        measurements.setMeasuredTime();
        measurements.setSensor_id(sensorRepositories.findByName(measurements.getSensor_id().getName()));
        measurementsRepositories.save(measurements);

    }

    @Transactional
    public List<Measurements> measurementsList(){
        return measurementsRepositories.findAll();
    }

    @Transactional
    public Integer raindyDayCounts(){
        int counter = 0;
        for ( Measurements measurements :  measurementsRepositories.findByRainingTrue()
        ){
            counter = counter + 1;
        }
        return counter;
    }
}
