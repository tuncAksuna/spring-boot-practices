package com.interview.tuncode.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Inheritance
@DiscriminatorColumn(name = "SCHOOL_TYPE" , discriminatorType = DiscriminatorType.STRING)
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    @Size(min = 8, max = 50)
    @NotEmpty(message = "{schoolName.notEmpty}")
    private String name;

    @Column(name = "CAPACITY", nullable = false)
    @NotEmpty(message = "{schoolCapacity.notEmpty}")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "SCHOOL_ADRESS_ID")
    private Address address;

}
