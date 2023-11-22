package com.harun.actorserviceapi.dto;

import com.harun.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Actor", description = "All details about the Actor")

public class ActorDTO extends BaseDTO<String> implements Serializable {
    @ApiModelProperty(example = "name", notes = "Name of the Actor")
    private String name;
    @ApiModelProperty(example = "lastname", notes = "Lastname of the Actor")
    private String lastName;
    @ApiModelProperty(notes = "Update Date of the Actor")
    private Date updateDate;
    @ApiModelProperty(example = "[\n" +
            "    \"Total Recall\", \n" +
            "    \"The Sorcerer's Apprentice\"\n" +
            "  ]", notes = "Movie Names Starring the Actors")
    private Set<String> movieTitles;
}
