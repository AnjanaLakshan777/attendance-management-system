package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.AttendanceEntity;
import edu.self.sams.entity.AttendanceId;

import java.util.ArrayList;

public interface AttendanceDao extends CrudDao<AttendanceEntity, AttendanceId> {
    public ArrayList<AttendanceEntity> getAttendanceByClassId(String classId) throws Exception;
}
