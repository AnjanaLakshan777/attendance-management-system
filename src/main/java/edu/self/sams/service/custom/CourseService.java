package edu.self.sams.service.custom;

import edu.self.sams.dto.CourseDto;
import edu.self.sams.service.SuperService;

import java.util.ArrayList;

public interface CourseService extends SuperService {
    public String saveCourse(CourseDto courseDto) throws Exception;
    public String updateCourse(CourseDto courseDto) throws Exception;
    public String deleteCourse(String courseCode) throws Exception;
    public CourseDto getCourse(String courseCode) throws Exception;
    public ArrayList<CourseDto> getCourses() throws Exception;
}
