package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.CourseDao;
import edu.self.sams.dao.custom.LecturerDao;
import edu.self.sams.dao.custom.ScheduleClassDao;
import edu.self.sams.dao.custom.SubjectDao;
import edu.self.sams.dto.ScheduleClassDto;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.entity.LecturerEntity;
import edu.self.sams.entity.ScheduleClassEntity;
import edu.self.sams.entity.SubjectEntity;
import edu.self.sams.service.custom.ScheduleClassService;

import java.util.ArrayList;

public class ScheduleClassServiceImpl implements ScheduleClassService {

    private ScheduleClassDao scheduleClassDao = (ScheduleClassDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.SCHEDULECLASS);
    private CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.COURSE);
    private SubjectDao subjectDao = (SubjectDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.SUBJECT);
    private LecturerDao lecturerDao = (LecturerDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.LECTURER);

    @Override
    public String saveScheduleClass(ScheduleClassDto scheduleClassDto) throws Exception {

        CourseEntity course = courseDao.get(scheduleClassDto.getCourseCode());
        SubjectEntity subject = subjectDao.get(scheduleClassDto.getSubjectCode());
        LecturerEntity lecturer = lecturerDao.get(scheduleClassDto.getUserId());

        ScheduleClassEntity scheduleClassEntity = new ScheduleClassEntity(generateNextClassId(),scheduleClassDto.getDate(),scheduleClassDto.getStartTime(),scheduleClassDto.getEndTime(),course,subject,lecturer);
        boolean isSaved =  scheduleClassDao.save(scheduleClassEntity);
        return isSaved ? "Class Schedule Successfully" : "Class Schedule Fail";
    }

    @Override
    public String updateScheduleClass(ScheduleClassDto scheduleClassDto) throws Exception {

        CourseEntity course = courseDao.get(scheduleClassDto.getCourseCode());
        SubjectEntity subject = subjectDao.get(scheduleClassDto.getSubjectCode());
        LecturerEntity lecturer = lecturerDao.get(scheduleClassDto.getUserId());

        ScheduleClassEntity scheduleClassEntity = new ScheduleClassEntity(scheduleClassDto.getClassId(),scheduleClassDto.getDate(),scheduleClassDto.getStartTime(),scheduleClassDto.getEndTime(),course,subject,lecturer);
        boolean isUpdated =  scheduleClassDao.update(scheduleClassEntity);
        return isUpdated ? "Class Schedule Update Successfully" : "Class Schedule Update Fail";
    }

    @Override
    public String deleteScheduleClass(String classId) throws Exception {
        boolean isDeleted =  scheduleClassDao.delete(classId);
        return isDeleted ?  "Class Schedule Delete Successfully" : "Class Schedule Delete Fail";
    }

    @Override
    public ArrayList<ScheduleClassDto> getAllScheduleClass() throws Exception {
        ArrayList<ScheduleClassEntity> scheduleClassEntities = scheduleClassDao.getAll();
        if(scheduleClassEntities!=null){
            ArrayList<ScheduleClassDto> scheduleClassDtos = new ArrayList<>();
            for(ScheduleClassEntity scheduleClassEntity:scheduleClassEntities){
                scheduleClassDtos.add(new ScheduleClassDto(scheduleClassEntity.getClassId(),scheduleClassEntity.getCourse().getCourseCode(),scheduleClassEntity.getSubject().getSubjectCode(),scheduleClassEntity.getLecturer().getUserId(),scheduleClassEntity.getDate(),scheduleClassEntity.getStartTime(),scheduleClassEntity.getEndTime()));
            }
            return scheduleClassDtos;
        }
        return null;
    }

    private String generateNextClassId(){
        String lastId = scheduleClassDao.getLastClassId();
        if (lastId == null) {
            return "CL001";
        }
        int num = Integer.parseInt(lastId.substring(2));
        num++;

        return String.format("CL%03d", num);
    }
}
