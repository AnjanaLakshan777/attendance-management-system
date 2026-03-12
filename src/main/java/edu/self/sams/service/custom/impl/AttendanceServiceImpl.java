package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.AttendanceDao;
import edu.self.sams.dto.AttendanceDto;
import edu.self.sams.entity.AttendanceEntity;
import edu.self.sams.entity.AttendanceId;
import edu.self.sams.entity.ScheduleClassEntity;
import edu.self.sams.entity.StudentEntity;
import edu.self.sams.service.custom.AttendanceService;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;

public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceDao attendanceDao = (AttendanceDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.ATTENDANCE);

    @Override
    public boolean saveAttendance(AttendanceDto attendanceDto) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            ScheduleClassEntity scheduleClass = session.find(ScheduleClassEntity.class, attendanceDto.getClassId());
            StudentEntity student = session.find(StudentEntity.class, attendanceDto.getRegNo());
            
            if (scheduleClass == null || student == null) {
                return false;
            }
            
            AttendanceId attendanceId = new AttendanceId(attendanceDto.getClassId(), attendanceDto.getRegNo());
            AttendanceEntity attendanceEntity = new AttendanceEntity(
                attendanceId,
                scheduleClass,
                student,
                attendanceDto.getStatus(),
                attendanceDto.getRemark()
            );
            
            return attendanceDao.save(attendanceEntity);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String saveAttendanceList(ArrayList<AttendanceDto> attendanceDtos) throws Exception {
        int savedCount = 0;
        int failedCount = 0;
        
        for (AttendanceDto dto : attendanceDtos) {
            try {
                boolean result = saveAttendance(dto);
                if (result) {
                    savedCount++;
                } else {
                    failedCount++;
                }
            } catch (Exception e) {
                failedCount++;
            }
        }
        
        return String.format("Attendance submission completed: %d saved, %d failed", savedCount, failedCount);
    }

    @Override
    public String updateAttendance(AttendanceDto attendanceDto) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            ScheduleClassEntity scheduleClass = session.find(ScheduleClassEntity.class, attendanceDto.getClassId());
            StudentEntity student = session.find(StudentEntity.class, attendanceDto.getRegNo());
            
            if (scheduleClass == null || student == null) {
                return "Invalid class or student";
            }
            
            AttendanceId attendanceId = new AttendanceId(attendanceDto.getClassId(), attendanceDto.getRegNo());
            AttendanceEntity attendanceEntity = new AttendanceEntity(
                attendanceId,
                scheduleClass,
                student,
                attendanceDto.getStatus(),
                attendanceDto.getRemark()
            );
            
            boolean isUpdated = attendanceDao.update(attendanceEntity);
            return isUpdated ? "Attendance Updated Successfully" : "Attendance Update Failed";
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String deleteAttendance(String classId, String regNo) throws Exception {
        AttendanceId attendanceId = new AttendanceId(classId, regNo);
        boolean isDeleted = attendanceDao.delete(attendanceId);
        return isDeleted ? "Attendance Deleted Successfully" : "Attendance Delete Failed";
    }

    @Override
    public AttendanceDto getAttendance(String classId, String regNo) throws Exception {
        AttendanceId attendanceId = new AttendanceId(classId, regNo);
        AttendanceEntity attendanceEntity = attendanceDao.get(attendanceId);
        if (attendanceEntity != null) {
            return new AttendanceDto(
                attendanceEntity.getId().getClassId(),
                attendanceEntity.getId().getRegNo(),
                attendanceEntity.getStatus(),
                attendanceEntity.getRemark()
            );
        }
        return null;
    }

    @Override
    public ArrayList<AttendanceDto> getAllAttendance() throws Exception {
        ArrayList<AttendanceEntity> attendanceEntities = attendanceDao.getAll();
        if (attendanceEntities != null) {
            ArrayList<AttendanceDto> attendanceDtos = new ArrayList<>();
            for (AttendanceEntity entity : attendanceEntities) {
                attendanceDtos.add(new AttendanceDto(
                    entity.getId().getClassId(),
                    entity.getId().getRegNo(),
                    entity.getStatus(),
                    entity.getRemark()
                ));
            }
            return attendanceDtos;
        }
        return null;
    }
}
