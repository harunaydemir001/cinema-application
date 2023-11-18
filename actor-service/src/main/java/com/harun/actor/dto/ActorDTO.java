package com.harun.actor.dto;

import com.harun.common.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Actor", description = "All details about the Actor")
public class ActorDTO {
    @ApiModelProperty(example = "1", notes = "Id of the Actor")
    private String id;
    @ApiModelProperty(example = "name", notes = "Name of the Actor")
    private String name;
    @ApiModelProperty(example = "surname", notes = "Surname of the Actor")
    private String surname;
    @ApiModelProperty(notes = "Update Date of the Actor")
    private Date updateDate;
    @ApiModelProperty(example = "ACTIVE", notes = "Status of the Actor")
    private StatusEnum status;
}
