package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.StudentEntity;

import java.util.ArrayList;

public interface StudentDao extends CrudDao<StudentEntity,String> {
    ArrayList<StudentEntity> getStudentsByCourseAndBatch(String courseCode, int batch) throws Exception;
}
