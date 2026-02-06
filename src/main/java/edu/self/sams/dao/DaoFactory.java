package edu.self.sams.dao;

import edu.self.sams.dao.custom.impl.UserDaoImpl;

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
            default:
                return null;
        }
    }

    public enum daoType{
        USER
    }
}
