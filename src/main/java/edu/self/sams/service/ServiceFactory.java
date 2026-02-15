package edu.self.sams.service;

import edu.self.sams.service.custom.impl.CourseServiceImpl;
import edu.self.sams.service.custom.impl.SubjectServiceImpl;
import edu.self.sams.service.custom.impl.UserServiceImpl;

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
            default:
                return null;
        }
    }

    public enum ServiceType {
        USER,COURSE,SUBJECT
    }
}
