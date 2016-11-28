package fei.tcc.service;

import fei.tcc.dto.LocationUsageDto;
import fei.tcc.repository.LocationUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by thiagoretondar on 27/11/16.
 */
@Service
public class LocationUsageService {

    private LocationUsageRepository locationUsageRepository;

    @Autowired
    public LocationUsageService(LocationUsageRepository locationUsageRepository) {
        this.locationUsageRepository = locationUsageRepository;
    }

    public List<LocationUsageDto> getLocations(Integer userId) {
        List<LocationUsageDto> locations = locationUsageRepository.findAllAtLastDay(userId);

        return locations;
    }

}
