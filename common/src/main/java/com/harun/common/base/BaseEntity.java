package com.harun.common.base;

import com.harun.common.enums.StatusEnum;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated()
    private StatusEnum status = StatusEnum.ACTIVE;
}
