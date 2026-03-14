package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.AttendanceDao;
import edu.self.sams.entity.AttendanceEntity;
import edu.self.sams.entity.AttendanceId;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDaoImpl implements AttendanceDao {
    @Override
    public boolean save(AttendanceEntity attendanceEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(attendanceEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(AttendanceEntity attendanceEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(attendanceEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(AttendanceId attendanceId) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            AttendanceEntity attendanceEntity = session.find(AttendanceEntity.class, attendanceId);
            if (attendanceEntity != null) {
                session.remove(attendanceEntity);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public AttendanceEntity get(AttendanceId attendanceId) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.find(AttendanceEntity.class, attendanceId);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<AttendanceEntity> getAll() throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (ArrayList<AttendanceEntity>) session.createQuery("FROM AttendanceEntity", AttendanceEntity.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<AttendanceEntity> getAttendanceByClassId(String classId) throws Exception {
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();

            String hql = "FROM AttendanceEntity a WHERE a.scheduleClass.classId = :classId";

            Query<AttendanceEntity> query = session.createQuery(hql, AttendanceEntity.class);
            query.setParameter("classId", classId);

            List<AttendanceEntity> list = query.getResultList();

            return new ArrayList<>(list);

        }catch (HibernateException e){
            throw new RuntimeException(e);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }
}
