package com.tht.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
@Table
public class UserLocationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String address;

    @Column
    private Integer regionCode;

    @Column
    private Float lat;

    @Column
    private Float lon;

    @Builder(access = AccessLevel.PRIVATE)
    public UserLocationInfo(final Long idx, final String userUuid, final String address,
                            final Integer regionCode, final float lat, final float lon) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.address = address;
        this.regionCode = regionCode;
        this.lat = lat;
        this.lon = lon;
    }

    public static UserLocationInfo create(final String userUuid, final String address,
                                          final Integer regionCode, final float lat, final float lon) {

        return UserLocationInfo.builder()
            .userUuid(userUuid)
            .address(address)
            .regionCode(regionCode)
            .lat(lat)
            .lon(lon)
            .build();
    }

    public void updateLocation(final String address, final Integer regionCode, final float lat,
                               final float lon) {

        this.address = address;
        this.regionCode = regionCode;
        this.lat = lat;
        this.lon = lon;
    }

    public int getDistanceBetween(final float lat, final float lon) {

        double theta = this.lon - lon;
        double distance = 60 * 1.1515 * (180 / Math.PI) * Math.acos(
            Math.sin(this.lat * (Math.PI / 180)) * Math.sin(lat * (Math.PI / 180)) +
                Math.cos(this.lat * (Math.PI / 180)) * Math.cos(lat * (Math.PI / 180)) * Math.cos(theta * (Math.PI / 180))
        );

        return (int) Math.ceil(distance * 1609.344);
    }
}
