package com.interview.tuncode.model;

import com.interview.tuncode.commonutils.CommonUtils;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue(CommonUtils.EntityUtils.PRIMARY_SCHOOL)
public class PrimarySchool extends School {

}
