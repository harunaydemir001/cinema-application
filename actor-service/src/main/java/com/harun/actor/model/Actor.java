package com.harun.actor.model;

import com.harun.common.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private Date updateDate;
    private Set<String> movieTitles;
    private StatusEnum status = StatusEnum.ACTIVE;
}