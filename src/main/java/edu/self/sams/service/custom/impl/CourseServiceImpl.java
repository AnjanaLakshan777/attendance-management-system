package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.CourseDao;
import edu.self.sams.dto.CourseDto;
import edu.self.sams.dto.CourseSubjectDto;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.entity.SubjectEntity;
import edu.self.sams.service.custom.CourseService;

import java.util.ArrayList;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.COURSE);

    @Override
    public String saveCourse(CourseDto courseDto) throws Exception {
        CourseEntity courseEntity = new CourseEntity(courseDto.getCourseCode(),courseDto.getCourseName(),courseDto.getDuration());
        boolean isSaved = courseDao.save(courseEntity);
        return isSaved ? "Course Saved" : "Course Not Saved";
    }

    @Override
    public String updateCourse(CourseDto courseDto) throws Exception {
        CourseEntity courseEntity = new CourseEntity(courseDto.getCourseCode(),courseDto.getCourseName(),courseDto.getDuration());
        boolean isUpdated = courseDao.update(courseEntity);
        return isUpdated ? "Course Updated" : "Course Not Updated";
    }

    @Override
    public String deleteCourse(String courseCode) throws Exception {
        boolean isDeleted = courseDao.delete(courseCode);
        return isDeleted ? "Course Deleted" : "Course Not Deleted";
    }

    @Override
    public CourseDto getCourse(String courseCode) throws Exception {
        CourseEntity courseEntity = courseDao.get(courseCode);
        if(courseEntity != null){
            return new CourseDto(courseEntity.getCourseCode(),courseEntity.getCourseName(),courseEntity.getDuration());
        }
        return null;
    }

    @Override
    public ArrayList<CourseDto> getCourses() throws Exception {
        ArrayList<CourseEntity> courseEntities = courseDao.getAll();
        ArrayList<CourseDto> courseDtos = new ArrayList<>();
        if(courseEntities != null){
            for (CourseEntity courseEntity : courseEntities) {
                courseDtos.add(new CourseDto(courseEntity.getCourseCode(),courseEntity.getCourseName(),courseEntity.getDuration()));
            }
            return courseDtos;
        }
        return null;
    }

    @Override
    public String assignSubject(String courseCode, String subjectCode) throws Exception {
        boolean isAssigned = courseDao.assignSubject(courseCode, subjectCode);
        return isAssigned ? "Subject Assigned to Course" : "Subject Not Assigned";
    }

    @Override
    public String unassignSubject(String courseCode, String subjectCode) throws Exception {
        boolean isUnassigned = courseDao.unassignSubject(courseCode, subjectCode);
        return isUnassigned ? "Subject Unassigned from Course" : "Subject Not Unassigned";
    }

    @Override
    public ArrayList<CourseSubjectDto> findSubjectsByCourseCode(String courseCode) throws Exception {
        CourseEntity course = courseDao.findCourseWithSubjects(courseCode);
        String courseName = course.getCourseName();
        ArrayList<CourseSubjectDto> courseSubjectDtos = new ArrayList<>();
        if(course != null){
            for(SubjectEntity subject : course.getSubjects()){
                CourseSubjectDto dto = new CourseSubjectDto(courseCode, courseName, subject.getSubjectCode(), subject.getSubjectName());
                courseSubjectDtos.add(dto);
            }
        }
        return courseSubjectDtos;
    }
}
