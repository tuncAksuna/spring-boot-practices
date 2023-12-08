package com.interview.tuncode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {

    @CreatedBy
    protected String createdBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, updatable = false)
    @CreatedDate
    protected String createdTime;

    @LastModifiedBy
    protected String lastModifiedBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @LastModifiedDate
    protected String updatedTime;
}

