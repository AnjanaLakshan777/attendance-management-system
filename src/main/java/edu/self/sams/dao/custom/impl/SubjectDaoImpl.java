package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.SubjectDao;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.entity.SubjectEntity;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class SubjectDaoImpl implements SubjectDao {

    @Override
    public boolean save(SubjectEntity subjectEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(subjectEntity);
            transaction.commit();
            return true;
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(SubjectEntity subjectEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(subjectEntity);
            transaction.commit();
            return true;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String subjectCode) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            SubjectEntity subjectEntity = session.find(SubjectEntity.class, subjectCode);
            if(subjectEntity!=null){
                session.remove(subjectEntity);
                transaction.commit();
                return true;
            }
            return false;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public SubjectEntity get(String subjectCode) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            return session.find(SubjectEntity.class,subjectCode);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<SubjectEntity> getAll() throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM SubjectEntity",SubjectEntity.class);
            return new ArrayList<>(query.list());
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
