package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.StudentDao;
import edu.self.sams.entity.StudentEntity;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean save(StudentEntity studentEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(studentEntity);
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
    public boolean update(StudentEntity studentEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(studentEntity);
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
    public boolean delete(String regNo) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            StudentEntity studentEntity = session.find(StudentEntity.class, regNo);
            if(studentEntity!=null){
                session.remove(studentEntity);
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
    public StudentEntity get(String regNo) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            return session.find(StudentEntity.class,regNo);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<StudentEntity> getAll() throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM StudentEntity ",StudentEntity.class);
            return new ArrayList<>(query.list());
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<StudentEntity> getStudentsByCourseAndBatch(String courseCode, int batch) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT DISTINCT e.student FROM EnrollmentEntity e " +
                         "WHERE e.course.courseCode = :courseCode AND e.batch = :batch AND e.status = :status ";
            Query<StudentEntity> query = session.createQuery(hql, StudentEntity.class);
            query.setParameter("courseCode", courseCode);
            query.setParameter("batch", batch);
            query.setParameter("status", "Active");
            return new ArrayList<>(query.list());
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
