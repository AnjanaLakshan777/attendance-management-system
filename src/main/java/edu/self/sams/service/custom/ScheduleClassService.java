package edu.self.sams.service.custom;

import edu.self.sams.dto.ScheduleClassDto;
import edu.self.sams.service.SuperService;

import java.util.ArrayList;

public interface ScheduleClassService extends SuperService {
    public String saveScheduleClass(ScheduleClassDto scheduleClassDto) throws Exception;
    public String updateScheduleClass(ScheduleClassDto scheduleClassDto) throws Exception;
    public String deleteScheduleClass(String classId) throws Exception;
    public ArrayList<ScheduleClassDto> getAllScheduleClass() throws Exception;
    public ArrayList<ScheduleClassDto> getScheduleClassByUserId(String userId) throws Exception;
}
