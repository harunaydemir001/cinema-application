package com.harun.director.model;

import com.harun.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "director")
@Where(clause = "status != 'DELETED'")
@ApiModel(value = "Director", description = "All details about the Director")
public class Director extends BaseEntity {
    @NotNull
    private String name;
    @NotNull
    private String surname;
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
