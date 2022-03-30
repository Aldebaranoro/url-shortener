package com.github.aldebaranoro.urlshortener.web.exception;

import org.zalando.problem.AbstractThrowableProblem;

import static java.text.MessageFormat.format;
import static org.zalando.problem.Status.CONFLICT;

public class CustomLinkAlreadyExistException extends AbstractThrowableProblem {

    private static final String MESSAGE_TEMPLATE = "Custom link with name ''{0}'' already exists";

    public CustomLinkAlreadyExistException(final String linkName) {
        super(null, "Custom link already exists", CONFLICT, format(MESSAGE_TEMPLATE, linkName));
    }
}
