package com.harun.movie.model;

import com.harun.common.base.BaseEntity;
import com.harun.movie.enums.RatingEnums;
import com.harun.movie.enums.SpecialFeaturesEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@Where(clause = "status != 'DELETED'")
@ApiModel(value = "Movie", description = "All details about the Movie Object")
public class Movie extends BaseEntity {

    @NotNull
    private String title;

    private String description;

    @Column(name = "release_date")
    private Date releaseDate;

    @NotNull
    @Column(name = "language_id")
    private Long languageId;

    @NotNull
    @Column(name = "rental_duration")
    private Integer rentalDuration = 3;

    @NotNull
    @Column(name = "rental_rate")
    private Double rentalRate = 4.99;

    private Integer length;

    @Column(name = "replacement_cost")
    private Double replacementCost = 19.99;

    @Enumerated
    private RatingEnums rating = RatingEnums.G;

    @Column(name = "special_features")
    @ElementCollection
    private Set<SpecialFeaturesEnum> specialFeatures;

    @NotNull
    @Column(name = "last_update")
    private Date lastUpdate;
}
