package com.rocketseat.redirectUrlShortner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlData {
    private String originalUrl;
    private long expirationTime;
}
