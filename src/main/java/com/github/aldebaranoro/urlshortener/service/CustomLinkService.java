package com.github.aldebaranoro.urlshortener.service;

import com.github.aldebaranoro.urlshortener.repository.CustomLinkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomLinkService {

    private final CustomLinkRepository repository;

    @Value("${custom-link.generated-name.size}")
    private Integer generatedLinkSize;

    @Transactional(readOnly = true)
    public boolean existCustomLinkByName(final String name) {
        return repository.existsCustomLinkByName(name);
    }

    @Transactional
    public String getUniqRandomLinkName() {
        return RandomStringUtils.randomAlphanumeric(generatedLinkSize);
    }
}
