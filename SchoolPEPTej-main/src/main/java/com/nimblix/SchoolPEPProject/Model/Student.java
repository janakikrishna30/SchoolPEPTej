package com.nimblix.SchoolPEPProject.Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "students")
@DiscriminatorValue("STUDENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends User {

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "section")
    private String section;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "roll_no")
    private Long rollNo;

    @Column(name = "admission_no")
    private Long admissionNo;

    @Column(name = "registration_no")
    private Long registrationNo;


    public void setEmail(String email) {
    }
}
