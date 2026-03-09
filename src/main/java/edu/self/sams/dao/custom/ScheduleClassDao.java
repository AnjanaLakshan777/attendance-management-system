package edu.self.sams.dao.custom;

import edu.self.sams.dao.CrudDao;
import edu.self.sams.entity.ScheduleClassEntity;

public interface ScheduleClassDao extends CrudDao<ScheduleClassEntity,String> {
    public String getLastClassId();
}
