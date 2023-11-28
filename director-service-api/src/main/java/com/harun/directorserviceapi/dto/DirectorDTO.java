package com.harun.directorserviceapi.dto;

import com.harun.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Schema(name = "Director", description = "All details about the Director")
public class DirectorDTO extends BaseDTO<Long> implements Serializable {
    @Schema(example = "name", description = "Name of the Director")
    private String name;
    @Schema(example = "lastname", description = "Lastname of the Director")
    private String lastName;
    @Schema(example = "Oscar", description = "Prize of the Director")
    private String prize;
    @Schema(example = "25", description = "Age of the Director", allowableValues = "range[1, 120]")
    private Integer age;
    @Schema(description = "BirthDay of the Director")
    private Date birthDay;
    @Schema(description = "Last Update of the Director")
    private Date lastUpdate;
    @Schema(example = "harunaydemir001@gmail.com", description = "Director Email")
    private String email;
    @Schema(example = "05111111111", description = "Director Phone Number")
    private String phoneNumber;
}