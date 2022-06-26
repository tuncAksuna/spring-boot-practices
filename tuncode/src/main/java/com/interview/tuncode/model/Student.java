package com.interview.tuncode.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 20)
    @NotEmpty(message = "{firsName.notempty}")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 11)
    @NotEmpty(message = "{lastName.notempty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @NotEmpty(message = "{email.notempty}")
    @Column(name = "email", unique = true, updatable = false, nullable = false)
    private String email;

    @Column(name = "created_time", nullable = false)
    private String createdTime;

    @Column(name = "is_updated")
    private boolean isUpdated;

}
