package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.SubjectEntity;

public interface SubjectDao extends CrudDao<SubjectEntity,String>{
    SubjectEntity findSubjectWithCourses(String subjectCode) throws Exception;
}