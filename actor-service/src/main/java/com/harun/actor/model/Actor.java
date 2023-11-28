package com.harun.actor.model;

import com.harun.common.constant.GeneralErrorCodeConstants;
import com.harun.common.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Document(collection = "actor")
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Actor", description = "All details about the Actor Object")
public class Actor implements Serializable {
    @Id
    @Indexed(unique = true)
    private String id;
    @NotBlank
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    private String name;
    @NotBlank
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    private String lastName;
    @NotNull
    private Date updateDate;
    private Set<String> movieTitles;
    private StatusEnum status = StatusEnum.ACTIVE;
}