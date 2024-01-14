package Project3TZServer.ServerSide.Repositories;

import Project3TZServer.ServerSide.Models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementsRepositories extends JpaRepository<Measurements, Integer> {
    public List<Measurements> findByRainingTrue();
}
