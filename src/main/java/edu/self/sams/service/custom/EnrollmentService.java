package edu.self.sams.service.custom;

import edu.self.sams.dto.EnrollmentDto;
import edu.self.sams.dto.TblEnrollmentDto;
import edu.self.sams.service.SuperService;

import java.util.ArrayList;

public interface EnrollmentService extends SuperService {
    String enrollStudent(EnrollmentDto enrollmentDto) throws Exception;
    String updateEnrollment(EnrollmentDto enrollmentDto) throws Exception;
    String deleteEnrollment(String courseCode, String regNo) throws Exception;
    ArrayList<TblEnrollmentDto> getAllEnrollments() throws Exception;
    ArrayList<TblEnrollmentDto> findEnrollmentsByCourseCode(String courseCode) throws Exception;
    ArrayList<TblEnrollmentDto> findEnrollmentsByStudentRegNo(String studentRegNo) throws Exception;
}
