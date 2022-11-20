package com.interview.tuncode.model;

import com.interview.tuncode.configurations.jpa.PasswordConverter;
import com.interview.tuncode.model.enums.EStudentRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 20)
    @NotEmpty(message = "{firsName.notempty}")
    private String firstName;

    @Size(max = 11)
    @NotEmpty(message = "{lastName.notempty")
    private String lastName;

    @Email
    @NotEmpty(message = "{email.notempty}")
    private String email;

    private String createdTime;

    private boolean isUpdated;

    @Column(unique = true)
    private String username;

    @Convert(converter = PasswordConverter.class)
    private String secretText;

    @Enumerated(EnumType.STRING)
    private EStudentRole studentRole;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretText() {
        return secretText;
    }

    public void setSecretText(String secretText) {
        this.secretText = secretText;
    }

    public EStudentRole getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(EStudentRole studentRole) {
        this.studentRole = studentRole;
    }
}
