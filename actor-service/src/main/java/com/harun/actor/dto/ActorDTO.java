package com.harun.actor.dto;

import com.harun.actor.model.Actor;
import com.harun.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Actor}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Actor", description = "All details about the Actor")

public class ActorDTO extends BaseDTO<String> implements Serializable {
    @ApiModelProperty(example = "name", notes = "Name of the Actor")
    private String name;
    @ApiModelProperty(example = "surname", notes = "Surname of the Actor")
    private String surname;
    @ApiModelProperty(notes = "Update Date of the Actor")
    private Date updateDate;
}
