package edu.self.sams.service;

import edu.self.sams.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getService(ServiceType serviceType) {
        switch (serviceType) {
            case USER:
                return new UserServiceImpl();
            case COURSE:
                return new CourseServiceImpl();
            case SUBJECT:
                return new SubjectServiceImpl();
            case STUDENT:
                return new StudentServiceImpl();
            case LECTURER:
                return new LecturerServiceImpl();
            default:
                return null;
        }
    }

    public enum ServiceType {
        USER,COURSE,SUBJECT,STUDENT, LECTURER
    }
}
