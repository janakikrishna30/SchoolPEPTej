package com.nimblix.SchoolPEPProject.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;        // matches entity
    private String dateOfBirth;     // matches entity field name
    private String rollNo;
    private String admissionNo;
    private String registrationNo;
    private String className;       // matches entity
    private String section;
    private String profileImage;
    private Long schoolId;

}
