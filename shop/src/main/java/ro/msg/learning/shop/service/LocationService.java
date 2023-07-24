package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.exception.NotFoundException;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.*;

@Service
@AllArgsConstructor
public class LocationService {


    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;


    public List<LocationDto> findAllLocations() {
        List<Location> foundLocations = locationRepository.findAll();
        List<LocationDto> locationDtos = new ArrayList<LocationDto>();
        foundLocations.forEach(location -> {
            locationDtos.add(locationMapper.toDto(location));
        });
        return locationDtos;
    }


    public LocationDto createLocation(LocationDto locationDto) {
        Location savedLocation = locationRepository.save(locationMapper.toEntity(locationDto));
        locationDto.setLocationId(savedLocation.getLocationId());
        return locationDto;
    }


    public String putLocation(UUID locationId, LocationDto locationDto) throws NotFoundException {
        if (locationRepository.findById(locationId).isEmpty()) {
            throw new NotFoundException(LOCATION_NOT_FOUND);
        }
        Location foundLocation = locationRepository.findById(locationId).get();

        if (locationDto.getAddressCity() != null && !locationDto.getAddressCountry().isEmpty()) {
            foundLocation.setAddressCity(locationDto.getAddressCity());
        }
        if (locationDto.getName() != null && !locationDto.getName().isEmpty()) {
            foundLocation.setName(locationDto.getName());
        }
        if (locationDto.getAddressCounty() != null && !locationDto.getAddressCounty().isEmpty()) {
            foundLocation.setAddressCounty(locationDto.getAddressCounty());
        }
        if (locationDto.getAddressStreet() != null && !locationDto.getAddressStreet().isEmpty()) {
            foundLocation.setAddressCity(locationDto.getAddressCity());
        }
        if (locationDto.getAddressCountry() != null && !locationDto.getAddressCountry().isEmpty()) {
            foundLocation.setAddressCountry(locationDto.getAddressCountry());
        }
        locationRepository.save(foundLocation);
        return PATCHED_SUCCESSFULLY;
    }


    public String deleteLocation(UUID locationId) throws NotFoundException {
        if (locationRepository.findById(locationId).isEmpty()) {
            throw new NotFoundException(LOCATION_NOT_FOUND);
        }
        locationRepository.delete(locationRepository.findById(locationId).get());
        if (locationRepository.findById(locationId).isPresent())
            return DELETE_ERROR;
        return DELETED_SUCCESSFULLY;
    }
}
