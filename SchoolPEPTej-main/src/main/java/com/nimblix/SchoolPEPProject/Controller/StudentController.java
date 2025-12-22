package com.nimblix.SchoolPEPProject.Controller;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Response.StudentResponse;
import com.nimblix.SchoolPEPProject.Service.StudentService;
import com.nimblix.SchoolPEPProject.Model.User;
import com.nimblix.SchoolPEPProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final UserRepository userRepository; // To validate user role

    @GetMapping("/{studentId}/profile")
    public ResponseEntity<?> getStudentProfile(
            @PathVariable Long studentId,
            @RequestParam String email // logged-in user's email
    ) {
        User user = userRepository.findByEmailId(email)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Unauthorized"));
        }

        StudentResponse student = studentService.getStudentProfile(studentId);
        if (student == null) {
            Map<String, String> error = new HashMap<>();
            error.put(SchoolConstants.MESSAGE, "Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }


        if (!user.getRole().getRoleName().equalsIgnoreCase(SchoolConstants.TEACHER_ROLE) &&
                !user.getRole().getRoleName().equalsIgnoreCase(SchoolConstants.ADMIN_ROLE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("message", "Access denied"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", student);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{studentId}/profile")
    public ResponseEntity<?> updateStudentProfile(
            @PathVariable Long studentId,
            @RequestParam String email, // logged-in user's email
            @RequestBody StudentRegistrationRequest request) {

        User user = userRepository.findByEmailId(email)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Unauthorized"));
        }

        // Only Admin can edit
        if (!user.getRole().getRoleName().equalsIgnoreCase(SchoolConstants.ADMIN_ROLE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("message", "You are not authorized to edit student profile"));
        }

        try {
            studentService.updateStudentProfile(studentId, request);

            Map<String, String> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("message", "Student profile updated successfully");
            return ResponseEntity.ok(resp);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put(SchoolConstants.MESSAGE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
