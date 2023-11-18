package com.harun.common.model;

import com.harun.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseStatusAndIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated()
    StatusEnum status = StatusEnum.ACTIVE;
}
