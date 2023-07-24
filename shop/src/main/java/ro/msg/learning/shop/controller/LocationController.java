package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/locations")
@Validated
@RestController
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;



    @GetMapping
    public ResponseEntity<List<LocationDto>> getAll() {
        return new ResponseEntity<List<LocationDto>>(locationService.findAllLocations(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID locationId) throws LocationNotFoundException {
        return new ResponseEntity<>(locationService.deleteLocation(locationId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable UUID locationId, @RequestBody LocationDto locationDto) throws LocationNotFoundException {
        return new ResponseEntity<>(locationService.putLocation(locationId, locationDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocationDto> post(@RequestBody @Validated LocationDto locationDto) {
        return new ResponseEntity<>(locationService.createLocation(locationDto), HttpStatus.OK);
    }
}
