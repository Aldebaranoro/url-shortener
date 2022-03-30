package com.github.aldebaranoro.urlshortener.domain.customlink;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.aldebaranoro.urlshortener.domain.AbstractAuditingEntity;
import com.github.aldebaranoro.urlshortener.domain.Identifiable;
import com.github.aldebaranoro.urlshortener.domain.customlink.constraint.CustomLinkName;
import com.github.aldebaranoro.urlshortener.domain.shorturl.ShortUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "custom_link")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CustomLink extends AbstractAuditingEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_link_id_seq")
    @SequenceGenerator(name = "custom_link_id_seq", allocationSize = 15)
    private Long id;

    @CustomLinkName
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "short_url_id", nullable = false)
    private ShortUrl shortUrl;
}
