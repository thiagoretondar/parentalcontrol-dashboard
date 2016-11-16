package fei.tcc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by thiagoretondar on 16/11/16.
 */
@Entity
@Table(name = "location_used")
public class LocationUsedEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "datetime_used")
    private LocalDateTime datetime_used;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "user_id")
    private Long userId;

    public LocationUsedEntity(LocalDateTime datetime_used, Double latitude, Double longitude, Long userId) {
        this.datetime_used = datetime_used;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDatetime_used() {
        return datetime_used;
    }

    public void setDatetime_used(LocalDateTime datetime_used) {
        this.datetime_used = datetime_used;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}