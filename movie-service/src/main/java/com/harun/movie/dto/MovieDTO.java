package com.harun.movie.dto;

import com.harun.common.enums.StatusEnum;
import com.harun.movie.enums.RatingEnums;
import com.harun.movie.enums.SpecialFeaturesEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Movie", description = "All details about the Movie Object")
public class MovieDTO {
    @ApiModelProperty(example = "1", notes = "Id of the Movie Object")
    private Long id;
    @ApiModelProperty(example = "title", notes = "Title of the Movie Object")
    private String title;
    @ApiModelProperty(example = "description", notes = "Description of the Movie Object")
    private String description;
    @ApiModelProperty("Release Date of the Movie Object")
    private Date releaseDate;
    @ApiModelProperty(example = "1", notes = "Language Id")
    private Long languageId;
    @ApiModelProperty(example = "3", notes = "Rental Duration of the Movie Object")
    private Integer rentalDuration = 3;
    @ApiModelProperty(example = "4.99", notes = "Rental Rate of the Movie Object")
    private Double rentalRate = 4.99;
    @ApiModelProperty(example = "120", notes = "Length of the Movie Object")
    private Integer length;
    @ApiModelProperty(example = "19.99", notes = "Replacement Cost of the Movie Object")
    private Double replacementCost = 19.99;
    @ApiModelProperty(example = "G", notes = "Rating Cost of the Movie Object")
    private RatingEnums rating = RatingEnums.G;
    @ApiModelProperty(example = "TRAILERS", notes = "Special Features of the Movie Object")
    private Set<SpecialFeaturesEnum> specialFeatures;
    @ApiModelProperty("Last Update of the Movie Object")
    private Date lastUpdate;
    @ApiModelProperty(example = "ACTIVE", notes = "Status of the Movie Object")
    private StatusEnum status;
}
