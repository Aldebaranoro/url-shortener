package com.github.aldebaranoro.urlshortener.web;

import com.github.aldebaranoro.urlshortener.service.ShortUrlService;
import com.github.aldebaranoro.urlshortener.service.dto.shorturl.ShortUrlCreate;
import com.github.aldebaranoro.urlshortener.service.dto.shorturl.ShortUrlInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.aldebaranoro.urlshortener.web.Routers.ShortUrl.API_SHORTEN;

@RestController
@RequestMapping(API_SHORTEN)
@RequiredArgsConstructor
public class UrlShortenerController {

    private final ShortUrlService service;

    @PostMapping
    public ResponseEntity<ShortUrlInfo> shorten(@Valid @RequestBody ShortUrlCreate shortUrlCreate) {
        return service.existsShortUrlByLongUrl(shortUrlCreate.longUrl())
                ? updateShortUrl(shortUrlCreate)
                : createShortUrl(shortUrlCreate);
    }

    private ResponseEntity<ShortUrlInfo> updateShortUrl(ShortUrlCreate shortUrlCreate) {
        return new ResponseEntity<>(service.updateShortUrl(shortUrlCreate), HttpStatus.OK);
    }

    private ResponseEntity<ShortUrlInfo> createShortUrl(ShortUrlCreate shortUrlCreate) {
        return new ResponseEntity<>(service.createShortUrl(shortUrlCreate), HttpStatus.CREATED);
    }
}
