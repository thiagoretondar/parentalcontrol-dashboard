package fei.tcc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by thiagoretondar on 16/11/16.
 */
@Entity
@Table(name = "app_usage")
public class AppUsageEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "datetime_used")
    private LocalDateTime dateTimeUsed;

    @Column(name = "user_id")
    private Long userId;

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

    public LocalDateTime getDateTimeUsed() {
        return dateTimeUsed;
    }

    public void setDateTimeUsed(LocalDateTime dateTimeUsed) {
        this.dateTimeUsed = dateTimeUsed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
