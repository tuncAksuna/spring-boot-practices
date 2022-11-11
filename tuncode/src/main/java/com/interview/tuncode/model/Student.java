package com.interview.tuncode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Builder

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

    public String toString() {
        return "Student(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", createdTime=" + this.getCreatedTime() + ", isUpdated=" + this.isUpdated() + ")";
    }

    public long getId() {
        return this.id;
    }

    public @Size(max = 20) @NotEmpty(message = "{firsName.notempty}") String getFirstName() {
        return this.firstName;
    }

    public @Size(max = 11) @NotEmpty(message = "{lastName.notempty") String getLastName() {
        return this.lastName;
    }

    public @Email @NotEmpty(message = "{email.notempty}") String getEmail() {
        return this.email;
    }

    public String getCreatedTime() {
        return this.createdTime;
    }

    public boolean isUpdated() {
        return this.isUpdated;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(@Size(max = 20) @NotEmpty(message = "{firsName.notempty}") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@Size(max = 11) @NotEmpty(message = "{lastName.notempty") String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(@Email @NotEmpty(message = "{email.notempty}") String email) {
        this.email = email;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
}
