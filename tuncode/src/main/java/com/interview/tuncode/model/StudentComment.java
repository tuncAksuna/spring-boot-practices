package com.interview.tuncode.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@EntityListeners(AuditingEntityListener.class)
public class StudentComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 256)
    private String commentDetail;

    private Long numberOfLikeOfTheComment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_COMMENT_STATUS_ID")
    @Fetch(FetchMode.SELECT)
    private Status status;

    @ManyToOne(targetEntity = Student.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "id")
    private Student student;

}
