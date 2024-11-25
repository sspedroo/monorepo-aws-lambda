package com.rocketseat.createUrlShortner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UrlData {
    private String originalUrl;
    private long expirationTime;
}
