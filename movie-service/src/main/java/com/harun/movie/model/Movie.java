package com.harun.movie.model;

import com.harun.common.base.BaseEntity;
import com.harun.movie.enums.Genre;
import com.harun.movie.enums.Quality;
import com.harun.movie.enums.SpecialFeaturesEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@Where(clause = "status != 'DELETED'")
@ApiModel(value = "Movie", description = "All details about the Movie Object")
public class Movie extends BaseEntity {

    @NotNull
    private String name;

    private Integer length;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "imdb_point")
    private Double imdbPoint;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "special_feature_map", joinColumns = @JoinColumn(name = "special_features_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "special_features")
    private Map<SpecialFeaturesEnum, String> specialFeatures;

    @NotNull
    @Column(name = "last_update")
    private Date lastUpdate;

    @Enumerated
    private Genre genre;

    @Enumerated
    private Quality quality;

    private String language;

    @Column(name = "is_oscar")
    private Boolean isOscar;
}
