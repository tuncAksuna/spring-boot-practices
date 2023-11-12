package com.interview.tuncode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {

    @Column(nullable = false, updatable = false)
    @CreatedBy
    protected String createdBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, updatable = false)
    @CreatedDate
    protected String createdTime;

    @LastModifiedBy
    @Column(nullable = false, updatable = false)
    protected String lastModifiedBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    protected String updatedTime;
}

