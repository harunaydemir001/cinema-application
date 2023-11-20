package com.harun.common.base;

import com.harun.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Enumerated()
    private StatusEnum status = StatusEnum.ACTIVE;
}
