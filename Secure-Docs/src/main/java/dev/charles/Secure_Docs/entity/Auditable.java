package dev.charles.Secure_Docs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.charles.Secure_Docs.domains.RequestContext;
import dev.charles.Secure_Docs.exception.ApiException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class Auditable {

    @Id
    @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "primary_key_seq")
    @Column(name = "id", updatable = false)
    private long id;
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();
    @NotNull
    private long createdBy;
    @NotNull
    private long updatedBy;
    @NotNull
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "updated_at",nullable = false)
    @CreatedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        var userId = RequestContext.getUserId();
        if (userId == null){
            throw new ApiException("Cannot persist entity without user ID in request context for this thread");
        }
        setCreatedAt(now());
        setCreatedBy(userId);
        setUpdatedBy(userId);
        setUpdatedAt(now());
    }

    @PreUpdate
    public void beforeUpdate() {
        var userId = RequestContext.getUserId();
        if (userId == null){
            throw new ApiException("Cannot persist entity without user ID in request context for this thread");
        }
        setUpdatedAt(now());
        setUpdatedBy(userId);
    }
}
