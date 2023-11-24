package com.harun.common.base;

import com.harun.common.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseDTO<T> implements Serializable {
    @ApiModelProperty(example = "1", notes = "Id of the Object", hidden = true, readOnly = true)
    private T id;
    @ApiModelProperty(example = "ACTIVE", notes = "Status of the Object")
    private StatusEnum status;

    public void setId(T id) {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new IllegalStateException("ID can't be updated");
        }
    }
}
