package com.interview.tuncode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.tuncode.configurations.encoder.PasswordConverter;
import com.interview.tuncode.model.enums.EStudentRole;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;


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
    @NotBlank
    @Column(name = "FIRSTNAME")
    private String firstName;

    @Size(max = 11)
    @NotBlank
    @Column(name = "LASTNAME")
    private String lastName;

    @Email
    @NotEmpty(message = "{email.notempty}")
    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @Column(name = "IS_UPDATED")
    private boolean isUpdated;

    @NotBlank
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Convert(converter = PasswordConverter.class)
    @Column(name = "SECRET_TEXT")
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
    private Address address;

    @OneToMany(mappedBy = "student")
    private List<StudentComment> studentComments;
}
