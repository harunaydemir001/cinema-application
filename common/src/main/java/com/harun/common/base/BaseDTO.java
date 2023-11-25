package com.harun.common.base;

import com.harun.common.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Data
@MappedSuperclass
public abstract class BaseDTO<T> implements Serializable {
    @Schema(example = "1", description = "Id of the Object", hidden = true, accessMode = READ_ONLY)
    private T id;
    @Schema(example = "ACTIVE", description = "Status of the Object")
    private StatusEnum status;

    public void setId(T id) {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new IllegalStateException("ID can't be updated");
        }
    }
}
