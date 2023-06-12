package com.interview.tuncode.model;

import lombok.*;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
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
