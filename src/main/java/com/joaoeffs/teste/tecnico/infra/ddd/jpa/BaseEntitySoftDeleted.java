package com.joaoeffs.teste.tecnico.infra.ddd.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntitySoftDeleted {

    @JsonIgnore
    @Getter
    protected boolean deleted;

    protected void markAsDeleted() {
        deleted = true;
    }
}
