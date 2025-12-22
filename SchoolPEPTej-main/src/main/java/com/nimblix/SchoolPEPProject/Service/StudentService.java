package com.nimblix.SchoolPEPProject.Service;

import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Response.StudentDetailsResponse;
import com.nimblix.SchoolPEPProject.Response.StudentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<?> registerStudent(StudentRegistrationRequest studentRegistrationRequest);

    void deleteStudent(Long studentId);

    void updateStudentDetails(Long studentId, StudentRegistrationRequest request);

    List<StudentDetailsResponse> getStudentsBySchoolId(Long schoolId);

    StudentResponse getStudentProfile(Long studentId);

    void updateStudentProfile(Long studentId, StudentRegistrationRequest request);
}
