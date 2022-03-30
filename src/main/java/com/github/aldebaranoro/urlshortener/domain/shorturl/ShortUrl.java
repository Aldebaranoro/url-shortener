package com.github.aldebaranoro.urlshortener.domain.shorturl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.aldebaranoro.urlshortener.domain.AbstractAuditingEntity;
import com.github.aldebaranoro.urlshortener.domain.Identifiable;
import com.github.aldebaranoro.urlshortener.domain.customlink.CustomLink;
import com.github.aldebaranoro.urlshortener.domain.shorturl.constraint.LongUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "short_url")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ShortUrl extends AbstractAuditingEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "short_url_id_seq")
    @SequenceGenerator(name = "short_url_id_seq", allocationSize = 15)
    private Long id;

    @LongUrl
    @Column(name = "long_url", unique = true, nullable = false)
    private String longUrl;

    @OneToMany(mappedBy = "shortUrl", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<CustomLink> customLinks = new HashSet<>();

    public ShortUrl addCustomLink(final CustomLink customLink) {
        customLinks.add(customLink);
        customLink.setShortUrl(this);
        return this;
    }

    public ShortUrl removeCustomLink(final CustomLink customLink) {
        customLinks.remove(customLink);
        customLink.setShortUrl(null);
        return this;
    }
}
