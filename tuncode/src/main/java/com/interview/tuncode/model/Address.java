package com.interview.tuncode.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class Address extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUNTRY", nullable = false)
    @NotEmpty(message = "{address.countryNotEmpty}")
    private String country;

    @Column(name = "CITY", nullable = false)
    @NotEmpty(message = "{address.cityNotEmpty}")
    private String city;

    @Column(name = "OPEN_ADDRESS")
    private String openAddress;

    @Column(unique = true, nullable = false, updatable = false)
    @NotEmpty(message = "{address.shortDescriptionNotEmpty}")
    @Size(min = 3, max = 9)
    private String shortDescription;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_STATUS_ID")
    @Fetch(FetchMode.SELECT)
    private Status status;

}

















