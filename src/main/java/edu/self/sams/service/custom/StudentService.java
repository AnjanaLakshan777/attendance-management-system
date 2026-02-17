package edu.self.sams.service.custom;

import edu.self.sams.dto.StudentDto;
import edu.self.sams.service.SuperService;

import java.util.ArrayList;


public interface StudentService extends SuperService {
    public String saveStudent(StudentDto studentDto) throws Exception;
    public String updateStudent(StudentDto studentDto) throws Exception;
    public String deleteStudent(String regNo) throws Exception;
    public StudentDto getStudent(String regNo) throws Exception;
    public ArrayList<StudentDto> getStudents() throws Exception;
}
