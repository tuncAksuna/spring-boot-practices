package com.interview.tuncode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.tuncode.configurations.jpa.PasswordConverter;
import com.interview.tuncode.model.enums.EStudentRole;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotEmpty(message = "{firstName.notempty}")
    @NotNull
    private String firstName;

    @Size(max = 11)
    @NotEmpty(message = "{lastName.notempty}")
    @NotNull
    private String lastName;

    @Email
    @NotEmpty(message = "{email.notempty}")
    @NotNull
    private String email;

    @JsonIgnore
    private boolean isUpdated;

    @Column(unique = true)
    @NotEmpty
    @NotNull
    private String username;

    @Convert(converter = PasswordConverter.class)
    private String secretText;

    @Enumerated(EnumType.STRING)
    private EStudentRole studentRole;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_STATUS_ID")
    @Fetch(FetchMode.SELECT)
    private Status status;

    /**
     * TODO : Student güncellereken ya da create ederken yeni bir adres ekledik.
     * Create ederken address tablosuna da yeni bir kayıt mı atacak ?
     * Update ederken de mevcut bir kaydı merge'mü edecek ? Yani var olanı mı değiştirecek ?
     */
    @ManyToOne
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
                    fetch = FetchType.LAZY
            )
    @JoinColumn(name = "STUDENT_ADDRESS_ID")
    @ToString.Exclude
    private Address address;


}
