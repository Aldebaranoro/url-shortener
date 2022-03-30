package com.github.aldebaranoro.urlshortener.repository;

import com.github.aldebaranoro.urlshortener.domain.customlink.CustomLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomLinkRepository extends JpaRepository<CustomLink, Long> {

    boolean existsCustomLinkByName(String name);
}
