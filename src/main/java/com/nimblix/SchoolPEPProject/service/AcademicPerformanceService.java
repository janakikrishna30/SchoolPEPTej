package com.nimblix.SchoolPEPProject.service;

import com.nimblix.SchoolPEPProject.dto.AcademicPerformanceResponse;
import com.nimblix.SchoolPEPProject.dto.AcademicSubjectDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicPerformanceService {

    public AcademicPerformanceResponse getAcademicPerformance(Long studentId, int year) {

        List<AcademicSubjectDTO> subjects = new ArrayList<>();

        subjects.add(new AcademicSubjectDTO("Tamil", 65));
        subjects.add(new AcademicSubjectDTO("English", 78));
        subjects.add(new AcademicSubjectDTO("Social Science", 70));
        subjects.add(new AcademicSubjectDTO("Hindi", 74));
        subjects.add(new AcademicSubjectDTO("Maths", 80));

        int total = 0;
        for (AcademicSubjectDTO subject : subjects) {
            total += subject.getPercentage();
        }

        int average = total / subjects.size();



        return new AcademicPerformanceResponse(average, subjects);
    }
}

