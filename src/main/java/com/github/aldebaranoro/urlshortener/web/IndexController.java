package com.github.aldebaranoro.urlshortener.web;

import com.github.aldebaranoro.urlshortener.service.ShortUrlService;
import com.github.aldebaranoro.urlshortener.web.exception.ShortUrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.aldebaranoro.urlshortener.web.Routers.LINK_PATH;
import static com.github.aldebaranoro.urlshortener.web.Routers.REDIRECT;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {

    private final ShortUrlService service;

    @GetMapping(LINK_PATH)
    public String redirectToLongUrl(@PathVariable String link) {
        final var shortUrlInfo = service.getShortUrlInfoByLinkName(link)
                .orElseThrow(() -> new ShortUrlNotFoundException(link));

        return REDIRECT + shortUrlInfo.longUrl();
    }
}
