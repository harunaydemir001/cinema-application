package com.harun.director.dto;

import com.harun.common.base.BaseDTO;
import com.harun.director.model.Director;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@ApiModel(value = "Director", description = "All details about the Director")
public class DirectorDTO extends BaseDTO<Long> implements Serializable {
    @ApiModelProperty(example = "name", notes = "Name of the Director", required = true)
    private String name;
    @ApiModelProperty(example = "surname", notes = "Surname of the Director", required = true)
    private String surname;
    @ApiModelProperty(example = "Oscar", notes = "Prize of the Director")
    private String prize;
    @ApiModelProperty(example = "25", notes = "Age of the Director", allowableValues = "range[1, 120]")
    private Integer age;
    @ApiModelProperty(notes = "BirthDay of the Director")
    private Date birthDay;
    @ApiModelProperty(notes = "Last Update of the Director")
    private Date lastUpdate;
}