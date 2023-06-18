package com.interview.tuncode.model;

import com.interview.tuncode.commonutils.CommonConstants;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue(CommonConstants.PRIMARY_SCHOOL)
public class PrimarySchool extends School {

}
