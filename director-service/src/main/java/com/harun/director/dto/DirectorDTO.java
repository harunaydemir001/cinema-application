package com.harun.director.dto;

import com.harun.common.enums.StatusEnum;
import com.harun.director.model.Director;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Director}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Director", description = "All details about the Director")
public class DirectorDTO implements Serializable {
    @ApiModelProperty(example = "1", notes = "Id of the Director")
    private Long id;
    @ApiModelProperty(example = "ACTIVE", notes = "Status of the Director")
    private StatusEnum status;
    @ApiModelProperty(example = "name", notes = "Name of the Director")
    private String name;
    @ApiModelProperty(example = "surname", notes = "Surname of the Director")
    private String surname;
    @ApiModelProperty(example = "Oscar", notes = "Prize of the Director")
    private String prize;
    @ApiModelProperty(example = "25", notes = "Age of the Director")
    private Integer age;
    @ApiModelProperty(notes = "BirthDay of the Director")
    private Date birthDay;
    @ApiModelProperty(notes = "Last Update of the Director")
    private Date lastUpdate;
}