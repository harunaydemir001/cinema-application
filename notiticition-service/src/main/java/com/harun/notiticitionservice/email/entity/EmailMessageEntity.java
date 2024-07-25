package com.harun.notiticitionservice.email.entity;

import com.harun.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Builder(setterPrefix = "with")
@NoArgsConstructor()
@AllArgsConstructor()
@Table(name = "email")
@Where(clause = "status != 'DELETED'")
@Schema(name = "Email", description = "All details about the Email Object")
public class EmailMessageEntity extends BaseEntity implements Serializable {
    @Column(name = "`from`")
    private String from;
    @Column(name = "`to`")
    private String to;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;
}
