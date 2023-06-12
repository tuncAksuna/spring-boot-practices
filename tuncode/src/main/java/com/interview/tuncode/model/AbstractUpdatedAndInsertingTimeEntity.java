package com.interview.tuncode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractUpdatedAndInsertingTimeEntity {

    @Column(name = "CREATION_TIME")
    @JsonFormat(pattern = "dd/MM/yyyy")
    // temporarily string
    private String creationTime;

    @Column(name = "UPDATED_TIME")
    @JsonFormat(pattern = "dd/MM/yyyy")
    // temporarily string
    private String updatedTime;
}

