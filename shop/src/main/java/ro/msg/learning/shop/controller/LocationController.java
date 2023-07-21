package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/location")
@Validated
@RestController
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        return new ResponseEntity<List<LocationDto>>(locationService.findAllLocations(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable("id") UUID locationId) throws LocationNotFoundException {
        return locationService.deleteLocation(locationId);
    }

    @PutMapping("/{id}")
    public String putLocation (@PathVariable UUID locationId, @RequestBody LocationDto locationDto) throws LocationNotFoundException {
        return locationService.putLocation(locationId,locationDto);
    }
    @PostMapping("/new")
    public ResponseEntity<LocationDto> postLocation(@RequestBody @Validated LocationDto locationDto) {
        return new ResponseEntity<>(locationService.createLocation(locationDto),HttpStatus.OK);
    }
}
