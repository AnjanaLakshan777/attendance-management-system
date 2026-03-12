package edu.self.sams.dao;

import edu.self.sams.dao.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(daoType Type) {
        switch (Type){
            case USER:
                return new UserDaoImpl();
            case COURSE:
                return new CourseDaoImpl();
            case SUBJECT:
                return new SubjectDaoImpl();
            case STUDENT:
                return new StudentDaoImpl();
            case LECTURER:
                return new LecturerDaoImpl();
            case ENROLLMENT:
                return new EnrollmentDaoImpl();
            case SCHEDULECLASS:
                return new ScheduleClassDaoImpl();
            case ATTENDANCE:
                return new AttendanceDaoImpl();
            default:
                return null;
        }
    }

    public enum daoType{
        USER, COURSE, SUBJECT, STUDENT, LECTURER, ENROLLMENT, SCHEDULECLASS, ATTENDANCE
    }
}
