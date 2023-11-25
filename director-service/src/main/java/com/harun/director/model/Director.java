package com.harun.director.model;

import com.harun.common.base.BaseEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "director")
@Where(clause = "status != 'DELETED'")
@Schema(name = "Director", description = "All details about the Director")
public class Director extends BaseEntity implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    private String prize;
    @Positive(message = "Age must be a positive number")
    @Max(120)
    private Integer age;
    @Past(message = "Birth day must be in the past")
    @Column(name = "birth_day")
    private Date birthDay;
    @Column(name = "last_update")
    private Date lastUpdate;
}
