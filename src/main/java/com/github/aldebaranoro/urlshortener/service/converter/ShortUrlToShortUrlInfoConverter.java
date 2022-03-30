package com.github.aldebaranoro.urlshortener.service.converter;

import com.github.aldebaranoro.urlshortener.domain.customlink.CustomLink;
import com.github.aldebaranoro.urlshortener.domain.shorturl.ShortUrl;
import com.github.aldebaranoro.urlshortener.service.dto.shorturl.ShortUrlInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShortUrlToShortUrlInfoConverter implements Converter<ShortUrl, ShortUrlInfo> {

    /**
     * Convert the source object of type {@code ShortUrl} to target type {@code ShortUrlInfo}.
     *
     * @param source the source object to convert,
     *               which must be an instance of {@code ShortUrl} (never {@code null})
     * @return the converted object, which must be an instance of {@code ShortUrlInfo} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ShortUrlInfo convert(ShortUrl source) {
        final var shortLinks = source.getCustomLinks().stream()
                .map(CustomLink::getName)
                .collect(Collectors.toSet());
        return new ShortUrlInfo(
                source.getLongUrl(),
                shortLinks
        );
    }
}
