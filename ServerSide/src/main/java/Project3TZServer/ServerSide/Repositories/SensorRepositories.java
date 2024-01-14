package Project3TZServer.ServerSide.Repositories;

import Project3TZServer.ServerSide.Models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepositories extends JpaRepository<Sensor,Integer> {
    public Sensor findByName(String name);
}
