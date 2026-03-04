package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.CourseDao;
import edu.self.sams.dao.custom.EnrollmentDao;
import edu.self.sams.dao.custom.StudentDao;
import edu.self.sams.dto.EnrollmentDto;
import edu.self.sams.dto.TblEnrollmentDto;
import edu.self.sams.entity.*;
import edu.self.sams.service.custom.EnrollmentService;

import java.util.ArrayList;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentDao enrollmentDao = (EnrollmentDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.ENROLLMENT);
    private CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.COURSE);
    private StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.STUDENT);

    @Override
    public String enrollStudent(EnrollmentDto enrollmentDto) throws Exception {
        try {
            EnrollmentId enrollmentId = new EnrollmentId(enrollmentDto.getCourseCode(), enrollmentDto.getRegNo());

            CourseEntity course = courseDao.get(enrollmentDto.getCourseCode());
            StudentEntity student = studentDao.get(enrollmentDto.getRegNo());

            if (course == null) {
                return "Course not found";
            }
            if (student == null) {
                return "Student not found";
            }

            EnrollmentEntity enrollmentEntity = new EnrollmentEntity(enrollmentId, course, student, enrollmentDto.getBatch(),enrollmentDto.getStatus() );

            boolean isSaved = enrollmentDao.save(enrollmentEntity);
            return isSaved ? "Student enrolled successfully" : "Enrollment failed";
        } catch (Exception e) {
            throw new Exception("Error enrolling student: " + e.getMessage(), e);
        }
    }

    @Override
    public String updateEnrollment(EnrollmentDto enrollmentDto) throws Exception {
        try {
            EnrollmentId enrollmentId = new EnrollmentId(enrollmentDto.getCourseCode(), enrollmentDto.getRegNo());

            CourseEntity course = courseDao.get(enrollmentDto.getCourseCode());
            StudentEntity student = studentDao.get(enrollmentDto.getRegNo());

            if (course == null) {
                return "Course not found";
            }
            if (student == null) {
                return "Student not found";
            }

            EnrollmentEntity enrollmentEntity = new EnrollmentEntity(enrollmentId, course, student, enrollmentDto.getBatch(), enrollmentDto.getStatus());

            boolean isUpdated = enrollmentDao.update(enrollmentEntity);
            return isUpdated ? "Enrollment updated successfully" : "Enrollment update failed";
        } catch (Exception e) {
            throw new Exception("Error updating enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public String deleteEnrollment(String courseCode, String regNo) throws Exception {
        try {
            EnrollmentId enrollmentId = new EnrollmentId(courseCode, regNo);
            boolean isDeleted = enrollmentDao.delete(enrollmentId);
            return isDeleted ? "Enrollment deleted successfully" : "Enrollment not found";
        } catch (Exception e) {
            throw new Exception("Error deleting enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<TblEnrollmentDto> getAllEnrollments() throws Exception {
        try{
            ArrayList<TblEnrollmentEntity> enrollmentEntities = enrollmentDao.getAllEnrollments();
            if(enrollmentEntities != null){
                ArrayList<TblEnrollmentDto> enrollmentDtos = new ArrayList<>();
                for(TblEnrollmentEntity enrollmentEntity : enrollmentEntities){
                    enrollmentDtos.add(new TblEnrollmentDto(enrollmentEntity.getRegNo(),enrollmentEntity.getStudentName(),enrollmentEntity.getCourseCode(),enrollmentEntity.getCourseName(), enrollmentEntity.getBatch(),enrollmentEntity.getStatus()));
                }
                return enrollmentDtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<TblEnrollmentDto> findEnrollmentsByCourseCode(String courseCode) throws Exception {
        try{
            ArrayList<TblEnrollmentEntity> enrollmentEntities = enrollmentDao.findEnrollmentsByCourseCode(courseCode);
            if(enrollmentEntities != null){
                ArrayList<TblEnrollmentDto> enrollmentDtos = new ArrayList<>();
                for(TblEnrollmentEntity enrollmentEntity : enrollmentEntities){
                    enrollmentDtos.add(new TblEnrollmentDto(enrollmentEntity.getRegNo(),enrollmentEntity.getStudentName(),enrollmentEntity.getCourseCode(),enrollmentEntity.getCourseName(), enrollmentEntity.getBatch(),enrollmentEntity.getStatus()));
                }
                return enrollmentDtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<TblEnrollmentDto> findEnrollmentsByStudentRegNo(String studentRegNo) throws Exception {
        try{
            ArrayList<TblEnrollmentEntity> enrollmentEntities = enrollmentDao.findEnrollmentsByStudentRegNo(studentRegNo);
            if(enrollmentEntities != null){
                ArrayList<TblEnrollmentDto> enrollmentDtos = new ArrayList<>();
                for(TblEnrollmentEntity enrollmentEntity : enrollmentEntities){
                    enrollmentDtos.add(new TblEnrollmentDto(enrollmentEntity.getRegNo(),enrollmentEntity.getStudentName(),enrollmentEntity.getCourseCode(),enrollmentEntity.getCourseName(), enrollmentEntity.getBatch(),enrollmentEntity.getStatus()));
                }
                return enrollmentDtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}










