package com.harun.common.base;

import com.harun.common.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseDTO<T> {
    @ApiModelProperty(example = "1", notes = "Id of the Object", hidden = true, readOnly = true)
    private T id;
    @ApiModelProperty(example = "ACTIVE", notes = "Status of the Object")
    private StatusEnum status;
}
