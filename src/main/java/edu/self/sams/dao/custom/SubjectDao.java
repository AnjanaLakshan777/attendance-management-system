package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.SubjectEntity;

public interface SubjectDao extends CrudDao<SubjectEntity,String>{
    SubjectEntity findSubjectWithCourses(String subjectCode) throws Exception;

    boolean assignLecturer(String subjectCode,String userId) throws Exception;
    boolean unassignLecturer(String subjectCode,String userId) throws Exception;
    SubjectEntity findSubjectWithLecturers(String subjectCode) throws Exception;
    int getTotalSubjects() throws Exception;
}