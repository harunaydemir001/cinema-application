package com.harun.movie.model;

import com.harun.common.base.BaseEntity;
import com.harun.movieserviceapi.enums.Genre;
import com.harun.movieserviceapi.enums.Quality;
import com.harun.movieserviceapi.enums.SpecialFeaturesEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@Where(clause = "status != 'DELETED'")
@ApiModel(value = "Movie", description = "All details about the Movie Object")
public class Movie extends BaseEntity implements Serializable {

    @NotNull
    @Column(unique = true)
    private String title;

    @Positive
    @NotNull
    private Integer length;

    @PastOrPresent
    @Column(name = "release_date")
    @NotNull
    private Date releaseDate;

    @Positive(message = "Imdb Point must be a positive number")
    @Column(name = "imdb_point")
    private Double imdbPoint;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "special_feature_map", joinColumns = @JoinColumn(name = "special_features_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "special_features")
    private Map<SpecialFeaturesEnum, String> specialFeatures;

    @Column(name = "last_update")
    private Date lastUpdate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "genre_set", joinColumns = @JoinColumn(name = "genre_id"))
    @MapKeyEnumerated(EnumType.STRING)
    private Set<Genre> genre;

    @Enumerated
    private Quality quality;

    private String language;

    @Column(name = "is_oscar")
    private Boolean isOscar;

    @NotNull
    @Column(name = "actor_name")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "actor_names_set", joinColumns = @JoinColumn(name = "actor_names_id"))
    @MapKeyEnumerated(EnumType.STRING)
    private Set<String> actorNames;
}
