package com.nimblix.SchoolPEPProject.dto;

import java.util.List;

public class AttendancePerformanceResponse {
    private int averageAttendance;
    private List<AttendanceDayDTO> weekWise;

    public AttendancePerformanceResponse(int averageAttendance, List<AttendanceDayDTO> weekWise) {
        this.averageAttendance = averageAttendance;
        this.weekWise = weekWise;
    }

    public int getAverageAttendance() {
        return averageAttendance;
    }

    public List<AttendanceDayDTO> getWeekWise() {

        return weekWise;
    }
}
