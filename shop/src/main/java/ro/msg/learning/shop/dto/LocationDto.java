package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {
    private UUID locationId;
    private String name;
    private String addressCity;
    private String addressCountry;
    private String addressStreet;
    private String addressCounty;
}
