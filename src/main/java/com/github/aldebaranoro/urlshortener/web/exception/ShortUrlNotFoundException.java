package com.github.aldebaranoro.urlshortener.web.exception;

import org.zalando.problem.AbstractThrowableProblem;

import static java.text.MessageFormat.format;
import static org.zalando.problem.Status.NOT_FOUND;

public class ShortUrlNotFoundException extends AbstractThrowableProblem {

    private static final String LINK_NOT_FOUND_MSG = "Short url with link=''{0}'' not found";

    public ShortUrlNotFoundException(final String link) {
        super(null, "Short url not found", NOT_FOUND, format(LINK_NOT_FOUND_MSG, link));
    }
}
