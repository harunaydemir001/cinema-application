package com.harun.movie.dto;

import com.harun.common.base.BaseDTO;
import com.harun.movie.enums.Genre;
import com.harun.movie.enums.Quality;
import com.harun.movie.enums.SpecialFeaturesEnum;
import com.harun.movie.model.Movie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * DTO for {@link Movie}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@ApiModel(value = "Movie", description = "All details about the Movie Object")
public class MovieDTO extends BaseDTO<Long> implements Serializable {
    @ApiModelProperty(example = "title", notes = "Name of the Movie Object", required = true)
    private String title;
    @ApiModelProperty(notes = "Release Date of the Movie Object", required = true)
    private Date releaseDate;
    @ApiModelProperty(example = "120", notes = "Length of the Movie Object", allowableValues = "range[1, 120]", required = true)
    private Integer length;
    @ApiModelProperty(example = "{\n" +
            "        \"TRAILERS\": \"Movie Trailer\",\n" +
            "        \"SCENES\": \"Scenes\"\n" +
            "    }", notes = "Special Features of the Movie Object")
    private Map<SpecialFeaturesEnum, String> specialFeatures;
    @ApiModelProperty("Last Update of the Movie Object")
    private Date lastUpdate;
    @ApiModelProperty(example = "[\n" +
            "    \"ACTION\", \n" +
            "    \"SANCTIFICATION\"\n" +
            "  ]", notes = "Genre of the Movie Object")
    private Set<Genre> genre;
    @ApiModelProperty(example = "ENGLISH", notes = "Language of the Movie Object")
    private String language;
    @ApiModelProperty(example = "7.0", notes = "IMDB Point of the Movie Object")
    private Double imdbPoint;
    @ApiModelProperty(example = "HD", notes = "Quality of Movie Object")
    private Quality quality;
    @ApiModelProperty(example = "True", notes = "Whether the movie won an Oscar or not")
    private Boolean isOscar;
    @ApiModelProperty(example = "[\n" +
            "    \"Leonardo DiCaprio\", \n" +
            "    \"Tom Hardy\"\n" +
            "  ]",
            notes = "Actor Names Playing in the Movie")
    private Set<String> actorNames;
}
