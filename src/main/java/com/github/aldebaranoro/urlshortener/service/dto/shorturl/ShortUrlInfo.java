package com.github.aldebaranoro.urlshortener.service.dto.shorturl;

import java.util.HashSet;
import java.util.Set;

public record ShortUrlInfo(
        String longUrl,
        Set<String> shortLinks
) {
    public ShortUrlInfo() {
        this(null, new HashSet<>());
    }
}
