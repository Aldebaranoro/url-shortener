package com.github.aldebaranoro.urlshortener.service.dto.shorturl;

import com.github.aldebaranoro.urlshortener.domain.customlink.constraint.CustomLinkName;
import com.github.aldebaranoro.urlshortener.domain.shorturl.constraint.LongUrl;

public record ShortUrlCreate(
        @LongUrl String longUrl,
        @CustomLinkName String customLinkName
) {
}
