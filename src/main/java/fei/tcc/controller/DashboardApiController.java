package fei.tcc.controller;

import fei.tcc.dto.AppQuantityDto;
import fei.tcc.dto.LocationUsageDto;
import fei.tcc.dto.UsageTodayDto;
import fei.tcc.service.AppUsageService;
import fei.tcc.service.LocationUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by thiagoretondar on 27/11/16.
 */
@RestController
public class DashboardApiController {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private AppUsageService appUsageService;

    private LocationUsageService locationUsageService;

    @Autowired
    public DashboardApiController(AppUsageService appUsageService, LocationUsageService locationUsageService) {
        this.appUsageService = appUsageService;
        this.locationUsageService = locationUsageService;
    }

    @RequestMapping(value = "/user/{userId}/timestart/{timestart}/timeend/{timeend}", method = GET)
    public List<AppQuantityDto> getAppUsed(@PathVariable("userId") Integer userId,
                                           @PathVariable("timestart") @DateTimeFormat(pattern = DATETIME_FORMAT) LocalDateTime timeStart,
                                           @PathVariable("timeend") @DateTimeFormat(pattern = DATETIME_FORMAT) LocalDateTime timeEnd) {

        return appUsageService.getUsagesFrom(userId, timeStart, timeEnd);
    }

    @RequestMapping(value = "/user/{userId}/usage/today", method = GET)
    public List<UsageTodayDto> getUsageToday(@PathVariable("userId") Integer userdId) {
        List<UsageTodayDto> usageTodayDtoList = new ArrayList<>();
        HashMap<String, List<Integer>> usageToday = appUsageService.getUsageToday(userdId);

        usageToday.forEach((key, value) -> {
            usageTodayDtoList.add(new UsageTodayDto(key, value));
        });

        return usageTodayDtoList;
    }

    @RequestMapping(value = "/user/{userId}/locations")
    public List<LocationUsageDto> getUsageLocations(@PathVariable("userId") Integer userId) {
        return locationUsageService.getLocations(userId);
    }

}
