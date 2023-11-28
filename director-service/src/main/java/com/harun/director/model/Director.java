package com.harun.director.model;

import com.harun.common.base.BaseEntity;

import com.harun.director.annotation.phonenumber.PhoneNumberValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @Size(min = 3, max = 30)
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
    @Email
    @NotNull
    private String email;
    @Column(name = "phone_number")
    @PhoneNumberValidation
    private String phoneNumber;
}
