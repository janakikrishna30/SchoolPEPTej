package com.nimblix.SchoolPEPProject.ServiceImpl;

import com.nimblix.SchoolPEPProject.Model.Student;
import com.nimblix.SchoolPEPProject.Repository.StudentRepository;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Response.StudentDetailsResponse;
import com.nimblix.SchoolPEPProject.Response.StudentResponse;
import com.nimblix.SchoolPEPProject.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<?> registerStudent(StudentRegistrationRequest request) {

        Student student = Student.builder()
                .classId(Long.valueOf(request.getClassName()))
                .section(request.getSection())
                .dateOfBirth(request.getDateOfBirth())
                .rollNo(Long.valueOf(request.getRollNo()))
                .admissionNo(Long.valueOf(request.getAdmissionNo()))
                .registrationNo(Long.valueOf(request.getRegistrationNo()))
                .build();

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setMobile(request.getMobileNo());
        student.setSchoolId(request.getSchoolId());
        student.setStatus("ACTIVE");

        studentRepository.save(student);
        return ResponseEntity.ok("Student registered successfully");
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void updateStudentDetails(Long studentId, StudentRegistrationRequest request) {

    }

    @Override
    public List<StudentDetailsResponse> getStudentsBySchoolId(Long schoolId) {

        return studentRepository.findBySchoolId(schoolId)
                .stream()
                .map(student ->
                        StudentDetailsResponse.builder()
                                .id(student.getId())
                                .firstName(student.getFirstName())   // from User
                                .lastName(student.getLastName())     // from User
                                .emailId(student.getEmailId())         // from User
                                .mobile(student.getMobile())         // from User
                                .schoolId(student.getSchoolId())     // from User
                                .status(student.getStatus())         // from User
                                .classId(student.getClassId())       // from Student
                                .section(student.getSection())       // from Student
                                .roleName("STUDENT")
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentProfile(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return StudentResponse.builder()
                .studentId(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .dob(student.getDateOfBirth())
                .mobileNo(student.getMobile())
                .emailId(student.getEmailId())
                .rollNo(String.valueOf(student.getRollNo()))
                .admissionNo(String.valueOf(student.getAdmissionNo()))
                .registrationNo(String.valueOf(student.getRegistrationNo()))
                .className(String.valueOf(student.getClassId()))
                .section(student.getSection())
                .profileImage(student.getProfilePicture())
                .build();
    }

    @Override
    public void updateStudentProfile(Long studentId, StudentRegistrationRequest request) {

    }
}

