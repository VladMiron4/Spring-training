package ro.msg.learning.shop.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
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
