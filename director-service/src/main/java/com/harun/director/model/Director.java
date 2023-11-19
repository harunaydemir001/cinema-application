package com.harun.director.model;

import com.harun.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "director")
@Where(clause = "status != 'DELETED'")
@ApiModel(value = "Director", description = "All details about the Director")
public class Director extends BaseEntity {
}
