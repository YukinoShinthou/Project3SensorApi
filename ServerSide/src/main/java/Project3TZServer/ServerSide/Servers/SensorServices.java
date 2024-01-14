package Project3TZServer.ServerSide.Servers;

import Project3TZServer.ServerSide.Models.Sensor;
import Project3TZServer.ServerSide.Repositories.SensorRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SensorServices {
    private final SensorRepositories sensorRepositories;

    @Autowired
    public SensorServices(SensorRepositories sensorRepositories) {
        this.sensorRepositories = sensorRepositories;
    }

    @Transactional
    public void InsertSensor(Sensor sensor){
        sensorRepositories.save(sensor);
    }

    @Transactional
    public String getNameById(int id){
        Optional<Sensor> sensor = sensorRepositories.findById(id);
        return sensor.map(Sensor::getName).orElse(null);
    }

    @Transactional
    public Boolean checkName(String name){
        Optional<Sensor> foundSensor = Optional.ofNullable(sensorRepositories.findByName(name));
        return foundSensor.isPresent();
    }

}
