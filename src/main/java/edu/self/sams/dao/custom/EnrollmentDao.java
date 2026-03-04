package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.EnrollmentEntity;
import edu.self.sams.entity.EnrollmentId;
import edu.self.sams.entity.TblEnrollmentEntity;

import java.util.ArrayList;

public interface EnrollmentDao extends CrudDao<EnrollmentEntity, EnrollmentId> {
    public ArrayList<TblEnrollmentEntity> getAllEnrollments() throws Exception;
    public ArrayList<TblEnrollmentEntity> findEnrollmentsByCourseCode(String courseCode) throws Exception;
    public ArrayList<TblEnrollmentEntity> findEnrollmentsByStudentRegNo(String studentRegNo) throws Exception;
}