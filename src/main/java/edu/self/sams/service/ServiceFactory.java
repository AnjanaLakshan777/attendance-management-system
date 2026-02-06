package edu.self.sams.service;

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
            default:
                return null;
        }
    }

    public enum ServiceType {
        USER,
    }
}
