package com.joaoeffs.teste.tecnico.infra.ddd.jpa;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

    @Transient
    private transient int hash = 0;

    @JsonIgnore
    @Version
    @Column(name = "row_version")
    private int entityVersion;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "row_created_at")
    private LocalDateTime entityCreatedAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "row_updated_at")
    private LocalDateTime entityUpdatedAt;
}

