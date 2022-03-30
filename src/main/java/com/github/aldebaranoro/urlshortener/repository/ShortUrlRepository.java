package com.github.aldebaranoro.urlshortener.repository;

import com.github.aldebaranoro.urlshortener.domain.shorturl.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    boolean existsShortUrlByLongUrl(String longUrl);

    Optional<ShortUrl> getShortUrlByLongUrl(String longUrl);

    Optional<ShortUrl> getShortUrlByCustomLinks_Name(String name);
}
