package com.harun.actor.model;

import com.harun.common.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Actor", description = "All details about the Actor Object")
public class Actor {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private Date updateDate;

    private StatusEnum status = StatusEnum.ACTIVE;

}