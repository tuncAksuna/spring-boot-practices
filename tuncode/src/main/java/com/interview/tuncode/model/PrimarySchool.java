package com.interview.tuncode.model;

import com.interview.tuncode.commonutils.CommonUtils;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue(CommonUtils.EntityUtils.PRIMARY_SCHOOL)
public class PrimarySchool extends School {

}
