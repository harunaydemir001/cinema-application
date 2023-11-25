package com.harun.actorserviceapi.dto;

import com.harun.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Actor", description = "All details about the Actor")

public class ActorDTO extends BaseDTO<String> implements Serializable {
    @Schema(example = "name", description = "Name of the Actor")
    private String name;
    @Schema(example = "lastname", description = "Lastname of the Actor")
    private String lastName;
    @Schema(description = "Update Date of the Actor")
    private Date updateDate;
    @Schema(example = "[\n" +
            "    \"Total Recall\", \n" +
            "    \"The Sorcerer's Apprentice\"\n" +
            "  ]", description = "Movie Names Starring the Actors")
    private Set<String> movieTitles;
}
