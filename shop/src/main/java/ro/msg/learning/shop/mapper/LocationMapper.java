package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;

import java.util.UUID;

@Component
public class LocationMapper {

    public LocationDto toDto(Location location) {
        return LocationDto.builder()
                .addressCounty(location.getAddressCounty())
                .name(location.getName())
                .locationId(location.getLocationId().toString())
                .addressCountry(location.getAddressCountry())
                .addressCity(location.getAddressCity())
                .addressStreet(location.getAddressStreet())
                .build();
    }

    public Location toEntity(LocationDto locationDto) {
        return Location.builder()
                .addressCounty(locationDto.getAddressCounty())
                .name(locationDto.getName())
                .addressCountry(locationDto.getAddressCountry())
                .addressCity(locationDto.getAddressCity())
                .addressStreet(locationDto.getAddressStreet())
                .build();
    }
}
