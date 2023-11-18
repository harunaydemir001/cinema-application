package com.harun.movie.dto;

import com.harun.common.enums.StatusEnum;
import com.harun.movie.enums.Genre;
import com.harun.movie.enums.Quality;
import com.harun.movie.enums.SpecialFeaturesEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Movie", description = "All details about the Movie Object")
public class MovieDTO {
    @ApiModelProperty(example = "1", notes = "Id of the Movie Object")
    private Long id;
    @ApiModelProperty(example = "name", notes = "Name of the Movie Object")
    private String name;
    @ApiModelProperty("Release Date of the Movie Object")
    private Date releaseDate;
    @ApiModelProperty(example = "120", notes = "Length of the Movie Object")
    private Integer length;
    @ApiModelProperty(example = "\"TRAILERS\": \"Movie Trailer\", \"SCENES\": \"Scenes\"", notes = "Special Features of the Movie Object")
    private Map<SpecialFeaturesEnum, String> specialFeatures;
    @ApiModelProperty("Last Update of the Movie Object")
    private Date lastUpdate;
    @ApiModelProperty(example = "ACTIVE", notes = "Status of the Movie Object")
    private StatusEnum status;
    @ApiModelProperty(example = "COMEDY", notes = "Genre of the Movie Object")
    private Genre genre;
    @ApiModelProperty(example = "ENGLISH", notes = "Language of the Movie Object")
    private String language;
    @ApiModelProperty(example = "7.0", notes = "IMDB Point of the Movie Object")
    private Double imdbPoint;
    @ApiModelProperty(example = "HD", notes = "Quality of Movie Object")
    private Quality quality;
    @ApiModelProperty(example = "True", notes = "Whether the movie won an Oscar or not")
    private Boolean isOscar;
}
