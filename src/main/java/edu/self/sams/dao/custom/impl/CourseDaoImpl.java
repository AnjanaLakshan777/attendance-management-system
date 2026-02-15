package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.CourseDao;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {

    @Override
    public boolean save(CourseEntity courseEntity) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(courseEntity);
            transaction.commit();
            return true;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public boolean update(CourseEntity courseEntity) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(courseEntity);
            transaction.commit();
            return true;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }

    }

    @Override
    public boolean delete(String courseCode) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CourseEntity courseEntity = session.find(CourseEntity.class, courseCode);
            if(courseEntity!=null){
                session.remove(courseEntity);
                transaction.commit();
                return true;
            }
            return false;
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public CourseEntity get(String courseCode) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(CourseEntity.class,courseCode);
        }
    }

    @Override
    public ArrayList<CourseEntity> getAll() throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM CourseEntity",CourseEntity.class);
            return new ArrayList<>(query.list());
        }
    }
}
