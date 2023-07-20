package ro.msg.learning.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.service.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.*;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    LocationMapper locationMapper;
    @Override
    public List<LocationDto> findAllLocations() {
        List<Location> foundLocations = locationRepository.findAll();
        List<LocationDto>locationDtos=new ArrayList<LocationDto>();
        foundLocations.forEach(location -> {
            locationDtos.add(locationMapper.toDto(location));
        });
        return locationDtos;
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
         locationRepository.save(locationMapper.toEntity(locationDto));
         return locationDto;
    }

    @Override
    public String putLocation(UUID locationId, LocationDto locationDto) throws LocationNotFoundException {
        if (locationRepository.findById(locationId).isEmpty()){
            throw new LocationNotFoundException();
        }
        Location foundLocation=locationRepository.findById(locationId).get();

        if (locationDto.getAddressCity()!=null && !locationDto.getAddressCountry().isEmpty()){
            foundLocation.setAddressCity(locationDto.getAddressCity());
        }
        if (locationDto.getName()!=null && !locationDto.getName().isEmpty()){
            foundLocation.setName(locationDto.getName());
        }
        if (locationDto.getAddressCounty()!=null && !locationDto.getAddressCounty().isEmpty()){
            foundLocation.setAddressCounty(locationDto.getAddressCounty());
        }
        if (locationDto.getAddressStreet()!=null && !locationDto.getAddressStreet().isEmpty()){
            foundLocation.setAddressCity(locationDto.getAddressCity());
        }
        if (locationDto.getAddressCountry()!=null && !locationDto.getAddressCountry().isEmpty()){
            foundLocation.setAddressCountry(locationDto.getAddressCountry());
        }
        locationRepository.save(foundLocation);
        return PATCHED_SUCCESSFULLY;
    }

    @Override
    public String deleteLocation(UUID locationId) throws LocationNotFoundException {
        if (locationRepository.findById(locationId).isEmpty()){
            throw new LocationNotFoundException();
        }
        locationRepository.delete(locationRepository.findById(locationId).get());
        if (locationRepository.findById(locationId).isPresent())
            return DELETE_ERROR;
        return DELETED_SUCCESSFULLY;
    }
}
