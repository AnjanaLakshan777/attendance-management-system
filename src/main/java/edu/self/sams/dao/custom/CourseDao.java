package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.CourseEntity;

public interface CourseDao extends CrudDao<CourseEntity,String> {
    boolean assignSubject(String courseCode, String subjectCode) throws Exception;
    boolean unassignSubject(String courseCode, String subjectCode) throws Exception;
}
