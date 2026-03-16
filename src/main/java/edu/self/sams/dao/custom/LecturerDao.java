package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.LecturerEntity;

public interface LecturerDao extends CrudDao<LecturerEntity, String> {
    LecturerEntity findLecturerWithSubjects(String userId)  throws Exception;
    int getTotalLecturers() throws Exception;
}
