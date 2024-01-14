package Project3TZServer.ServerSide.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "measurements")
public class Measurements {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    @Range(min = -100, max = 100, message = "Temperature can be from -100 to 100")
    @NotNull
    private Float value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id" , referencedColumnName = "id")
    private Sensor sensor_id;

    @Column(name = "measured_time")
    private Timestamp measuredTime;

    public Measurements() {
    }

    public Measurements(float value, boolean raining, Sensor sensor_id) {
        this.value = value;
        this.raining = raining;
        this.sensor_id = sensor_id;
        this.measuredTime = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(Sensor sensor_id) {
        this.sensor_id = sensor_id;
    }

    public Timestamp getMeasuredTime() {
        return measuredTime;
    }

    public void setMeasuredTime() {
        this.measuredTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor_id=" + sensor_id +
                ", measuredTime=" + measuredTime +
                "\n" +
                '}';
    }
}
