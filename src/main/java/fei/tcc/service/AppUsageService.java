package fei.tcc.service;

import fei.tcc.dto.AppQuantityDto;
import fei.tcc.repository.AppUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;

/**
 * Created by thiagoretondar on 27/11/16.
 */
@Service
public class AppUsageService {

    private AppUsageRepository appUsageRepository;

    private Integer id;

    private Integer position;

    @Autowired
    public AppUsageService(AppUsageRepository appUsageRepository) {
        this.appUsageRepository = appUsageRepository;
    }

    public List<AppQuantityDto> getUsagesFrom(Integer userId, LocalDateTime timeStart, LocalDateTime timeEnd) {

        List<AppQuantityDto> result = new ArrayList<>();

        LinkedHashMap<String, Integer> quantityForEachApp = appUsageRepository.getQuantityForEachAppRepository(userId, timeStart, timeEnd);

        List<String> allAppsUsed = appUsageRepository.findAllAppsUsed(userId, timeEnd.minusDays(1), timeEnd);

        id = 0;
        quantityForEachApp.forEach((appname, quantity) -> {
            result.add(new AppQuantityDto(id, appname, quantity));
            id++;
        });
        allAppsUsed.forEach(app -> {
            if (!quantityForEachApp.containsKey(app)) {
                result.add(new AppQuantityDto(id, app, 0));
                id++;
            }
        });

        return result;
    }

    public HashMap<String, List<Integer>> getUsageToday(Integer userId) {
        HashMap<String, List<Integer>> appsUsedToday = new HashMap<>();
        List<Integer> hours = asList(3, 6, 9, 12, 15, 18, 21, 0);

        LocalDate today = LocalDate.now();

        position = 0;
        hours.forEach(hour -> {
            LocalDateTime hourEnd = LocalDateTime.of(today, LocalTime.of(hour, 0));
            LocalDateTime hourStart = hourEnd.minusHours(3);

            LinkedHashMap<String, Integer> quantityForEachAppRepository = appUsageRepository.getQuantityForEachAppRepository(userId, hourStart, hourEnd);
            quantityForEachAppRepository.forEach((appname, quantity) -> {
                if (appsUsedToday.containsKey(appname)) {
                    List<Integer> hoursList = appsUsedToday.get(appname);
                    hoursList.set(position, quantity);
                } else {
                    ArrayList<Integer> newHours = new ArrayList<>(nCopies(hours.size(), 0));
                    newHours.set(position, quantity);
                    appsUsedToday.put(appname, newHours);
                }
            });
            position++;
        });

        return appsUsedToday;
    }
}
