package com.harun.movieserviceapi.dto;

import com.harun.common.base.BaseDTO;
import com.harun.movieserviceapi.enums.Genre;
import com.harun.movieserviceapi.enums.Quality;
import com.harun.movieserviceapi.enums.SpecialFeaturesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Schema(name = "Movie", description = "All details about the Movie Object")
public class MovieDTO extends BaseDTO<Long> implements Serializable {
    @Schema(example = "title", description = "Name of the Movie Object")
    private String title;
    @Schema(description = "Release Date of the Movie Object")
    private Date releaseDate;
    @Schema(example = "120", description = "Length of the Movie Object", allowableValues = "range[1, 120]")
    private Integer length;
    @Schema(example = "{\n" +
            "        \"TRAILERS\": \"Movie Trailer\",\n" +
            "        \"SCENES\": \"Scenes\"\n" +
            "    }", description = "Special Features of the Movie Object")
    private Map<SpecialFeaturesEnum, String> specialFeatures;
    @Schema(description = "Last Update of the Movie Object")
    private Date lastUpdate;
    @Schema(example = "[\n" +
            "    \"ACTION\", \n" +
            "    \"SANCTIFICATION\"\n" +
            "  ]", description = "Genre of the Movie Object")
    private Set<Genre> genre;
    @Schema(example = "ENGLISH", description = "Language of the Movie Object")
    private String language;
    @Schema(example = "7.0", description = "IMDB Point of the Movie Object")
    private Double imdbPoint;
    @Schema(example = "HD", description = "Quality of Movie Object")
    private Quality quality;
    @Schema(example = "True", description = "Whether the movie won an Oscar or not")
    private Boolean isOscar;
    @Schema(example = "[\n" +
            "    \"Leonardo DiCaprio\", \n" +
            "    \"Tom Hardy\"\n" +
            "  ]",
            description = "Actor Names Playing in the Movie")
    private Set<String> actorNames;
}
