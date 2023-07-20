package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    public List<LocationDto> findAllLocations();
    public LocationDto createLocation(LocationDto locationDto) ;
    public String putLocation(UUID locationId, LocationDto locationDto) throws  LocationNotFoundException;

    public String deleteLocation(UUID locationId) throws LocationNotFoundException;
}
