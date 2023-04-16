package com.interview.tuncode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.tuncode.configurations.jpa.PasswordConverter;
import com.interview.tuncode.model.enums.EStudentRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 20)
    @NotEmpty(message = "{firstName.notempty}")
    private String firstName;

    @Size(max = 11)
    @NotEmpty(message = "{lastName.notempty")
    private String lastName;

    @Email
    @NotEmpty(message = "{email.notempty}")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String createdTime;

    @JsonIgnore
    private boolean isUpdated;

    @Column(unique = true)
    private String username;

    @Convert(converter = PasswordConverter.class)
    private String secretText;

    @Enumerated(EnumType.STRING)
    private EStudentRole studentRole;


}
