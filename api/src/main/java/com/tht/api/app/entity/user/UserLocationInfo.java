package com.tht.api.app.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_location_info")
public class UserLocationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "address")
    private String address;

    @Column(name = "region_code")
    private Integer regionCode;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lon")
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
}
