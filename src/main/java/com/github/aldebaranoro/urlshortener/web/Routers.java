package com.github.aldebaranoro.urlshortener.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Routers {

    public static final String API = "/api";

    public static final String ID_PATH = "/{id}";

    public static final String REDIRECT = "redirect:";

    public static final String DEFAULT_SORT_FIELD = "createdDate";

    public static final String LINK_PATH = "/{link}";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShortUrl {

        public static final String API_SHORTEN = API + "/shorten";
    }
}
