package dev.charles.Secure_Docs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class Auditable {
    private long id;
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();
    private long createdBy;
    private long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
