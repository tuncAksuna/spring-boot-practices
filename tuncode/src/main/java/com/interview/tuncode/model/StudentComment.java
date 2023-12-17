package com.interview.tuncode.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @Column(name = "commentDetail")
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 256)
    private String commentDetail;

    @Column(name = "numberOfLikeOfTheComment")
    private Long numberOfLikeOfTheComment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_COMMENT_STATUS_ID")
    @Fetch(FetchMode.SELECT)
    private Status status;

    @ManyToOne(targetEntity = Student.class,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    @JsonIgnore
    private Student student;

}
