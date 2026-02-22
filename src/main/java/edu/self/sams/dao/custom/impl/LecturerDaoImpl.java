package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.LecturerDao;
import edu.self.sams.entity.LecturerEntity;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class LecturerDaoImpl implements LecturerDao {
    @Override
    public boolean save(LecturerEntity lecturerEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(lecturerEntity);
            transaction.commit();
            return true;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public boolean update(LecturerEntity lecturerEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(lecturerEntity);
            transaction.commit();
            return true;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String userId) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            LecturerEntity lecturerEntity = session.find(LecturerEntity.class, userId);
            if(lecturerEntity!=null){
                session.remove(lecturerEntity);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public LecturerEntity get(String userId) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            return session.find(LecturerEntity.class, userId);
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public ArrayList<LecturerEntity> getAll() throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LecturerEntity");
            return new ArrayList<>(query.list());
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }
}
