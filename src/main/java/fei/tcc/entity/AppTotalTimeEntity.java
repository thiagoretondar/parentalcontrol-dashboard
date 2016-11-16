package fei.tcc.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by thiagoretondar on 16/11/16.
 */
@Entity
@Table(name = "app_total_time")
public class AppTotalTimeEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "user_id")
    private Long userId;

    public AppTotalTimeEntity() {
    }

    public AppTotalTimeEntity(String appName, Long userId) {
        this.appName = appName;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
