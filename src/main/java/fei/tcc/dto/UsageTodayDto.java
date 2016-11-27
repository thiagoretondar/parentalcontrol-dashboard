package fei.tcc.dto;

import java.util.List;

/**
 * Created by thiagoretondar on 27/11/16.
 */
public class UsageTodayDto {

    private String appname;

    private List<Integer> hours;

    public UsageTodayDto(String appname, List<Integer> hours) {
        this.appname = appname;
        this.hours = hours;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public void setHours(List<Integer> hours) {
        this.hours = hours;
    }
}
