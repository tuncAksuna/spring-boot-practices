package com.interview.tuncode.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUNTRY", nullable = false)
    @NotBlank
    private String country;

    @Column(name = "CITY", nullable = false)
    @NotBlank
    private String city;

    @Column(name = "OPEN_ADDRESS")
    private String openAddress;

    @Column(unique = true,updatable = false)
    @NotBlank
    @Size(min = 3, max = 9)
    private String shortDescription;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_STATUS_ID")
    @Fetch(FetchMode.SELECT)
    private Status status;

}

















