package com.github.aldebaranoro.urlshortener.service;

import com.github.aldebaranoro.urlshortener.domain.customlink.CustomLink;
import com.github.aldebaranoro.urlshortener.domain.shorturl.ShortUrl;
import com.github.aldebaranoro.urlshortener.repository.ShortUrlRepository;
import com.github.aldebaranoro.urlshortener.service.dto.shorturl.ShortUrlCreate;
import com.github.aldebaranoro.urlshortener.service.dto.shorturl.ShortUrlInfo;
import com.github.aldebaranoro.urlshortener.web.exception.CustomLinkAlreadyExistException;
import com.github.aldebaranoro.urlshortener.web.exception.ShortUrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final ShortUrlRepository repository;

    private final CustomLinkService customLinkService;

    private final ConversionService conversionService;

    @Transactional(readOnly = true)
    public Optional<ShortUrlInfo> getShortUrlInfoByLinkName(final String linkName) {
        return repository.getShortUrlByCustomLinks_Name(linkName)
                .map(shortUrl -> conversionService.convert(shortUrl, ShortUrlInfo.class));
    }

    @Transactional(readOnly = true)
    public boolean existsShortUrlByLongUrl(final String longUrl) {
        return repository.existsShortUrlByLongUrl(longUrl);
    }

    @Transactional
    public ShortUrlInfo createShortUrl(final ShortUrlCreate shortUrlCreate) {
        if (shortUrlCreate.customLinkName() != null
                && customLinkService.existCustomLinkByName(shortUrlCreate.customLinkName())) {
            throw new CustomLinkAlreadyExistException(shortUrlCreate.customLinkName());
        }
        final var shortUrlToCreate = requireNonNull(conversionService.convert(shortUrlCreate, ShortUrl.class));
        if (shortUrlCreate.customLinkName() == null) {
            final var generatedLinkName = customLinkService.getUniqRandomLinkName();
            shortUrlToCreate.addCustomLink(new CustomLink().setName(generatedLinkName));
        }
        final var createdShortUrl = repository.save(shortUrlToCreate);
        return conversionService.convert(createdShortUrl, ShortUrlInfo.class);
    }

    @Transactional
    public ShortUrlInfo updateShortUrl(final ShortUrlCreate shortUrlCreate) {
        if (shortUrlCreate.customLinkName() != null
                && customLinkService.existCustomLinkByName(shortUrlCreate.customLinkName())) {
            throw new CustomLinkAlreadyExistException(shortUrlCreate.customLinkName());
        }
        final var shortUrlToUpdate = repository.getShortUrlByLongUrl(shortUrlCreate.longUrl())
                .orElseThrow(() -> new ShortUrlNotFoundException(shortUrlCreate.longUrl()));
        if (shortUrlCreate.customLinkName() == null) {
            final var generatedLinkName = customLinkService.getUniqRandomLinkName();
            shortUrlToUpdate.addCustomLink(new CustomLink().setName(generatedLinkName));
        } else {
            shortUrlToUpdate.addCustomLink(new CustomLink().setName(shortUrlCreate.customLinkName()));
        }
        final var updatedShortUrl = repository.save(shortUrlToUpdate);
        return conversionService.convert(updatedShortUrl, ShortUrlInfo.class);
    }
}
