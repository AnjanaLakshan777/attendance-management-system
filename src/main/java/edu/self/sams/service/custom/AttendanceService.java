package edu.self.sams.service.custom;

import edu.self.sams.dto.AttendanceDto;
import edu.self.sams.service.SuperService;

import java.util.ArrayList;

public interface AttendanceService extends SuperService {
    boolean saveAttendance(AttendanceDto attendanceDto) throws Exception;
    String saveAttendanceList(ArrayList<AttendanceDto> attendanceDtos) throws Exception;
    String updateAttendance(AttendanceDto attendanceDto) throws Exception;
    String deleteAttendance(String classId, String regNo) throws Exception;
    AttendanceDto getAttendance(String classId, String regNo) throws Exception;
    ArrayList<AttendanceDto> getAllAttendance() throws Exception;
}
