package com.github.aldebaranoro.urlshortener.service.converter;

import com.github.aldebaranoro.urlshortener.domain.customlink.CustomLink;
import com.github.aldebaranoro.urlshortener.domain.shorturl.ShortUrl;
import com.github.aldebaranoro.urlshortener.service.dto.shorturl.ShortUrlCreate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlCreateToShortUrlConverter implements Converter<ShortUrlCreate, ShortUrl> {

    /**
     * Convert the source object of type {@code ShortUrlCreate} to target type {@code ShortUrl}.
     *
     * @param source the source object to convert,
     *               which must be an instance of {@code ShortUrlCreate} (never {@code null})
     * @return the converted object, which must be an instance of {@code ShortUrl} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ShortUrl convert(ShortUrlCreate source) {
        final var shortUrl = new ShortUrl().setLongUrl(source.longUrl());
        if (source.customLinkName() != null) {
            final var customLink = new CustomLink().setName(source.customLinkName());
            shortUrl.addCustomLink(customLink);
        }
        return shortUrl;
    }
}
