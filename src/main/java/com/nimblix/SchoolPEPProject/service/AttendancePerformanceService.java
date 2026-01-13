package com.nimblix.SchoolPEPProject.service;

import com.nimblix.SchoolPEPProject.dto.AttendanceDayDTO;
import com.nimblix.SchoolPEPProject.dto.AttendancePerformanceResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendancePerformanceService {

    public AttendancePerformanceResponse getAttendancePerformance(Long studentId, String week) {

        List<AttendanceDayDTO> days = new ArrayList<>();

        days.add(new AttendanceDayDTO("Mon", true));
        days.add(new AttendanceDayDTO("Tue", true));
        days.add(new AttendanceDayDTO("Wed", false));
        days.add(new AttendanceDayDTO("Thu", true));
        days.add(new AttendanceDayDTO("Fri", true));

        int presentCount = 0;
        for (AttendanceDayDTO day : days) {
            if (day.isPresent()) {
                presentCount++;
            }
        }

        int averageAttendance = (presentCount * 100) / days.size();

        return new AttendancePerformanceResponse(averageAttendance, days);
    }
}
