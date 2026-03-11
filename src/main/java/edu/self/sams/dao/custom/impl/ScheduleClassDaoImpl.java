package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.ScheduleClassDao;
import edu.self.sams.entity.*;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ScheduleClassDaoImpl implements ScheduleClassDao {
    @Override
    public boolean save(ScheduleClassEntity scheduleClassEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(scheduleClassEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean update(ScheduleClassEntity scheduleClassEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(scheduleClassEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String classId) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            ScheduleClassEntity scheduleClassEntity = session.find(ScheduleClassEntity.class, classId);
            if(scheduleClassEntity != null){
                session.remove(scheduleClassEntity);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public ScheduleClassEntity get(String classId) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ScheduleClassEntity> getAll() throws Exception {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String hql = "FROM ScheduleClassEntity s " +
                    "JOIN FETCH s.course " +
                    "JOIN FETCH s.subject " +
                    "JOIN FETCH s.lecturer";

            Query<ScheduleClassEntity> query = session.createQuery(hql, ScheduleClassEntity.class);

            ArrayList<ScheduleClassEntity> scheduleClassList = (ArrayList)query.getResultList();

            return scheduleClassList;

        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getLastClassId() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String lastId = (String) session.createQuery("SELECT c.classId FROM ScheduleClassEntity c ORDER BY c.classId DESC").setMaxResults(1).uniqueResult();

        session.close();
        return lastId;
    }

    @Override
    public ArrayList<ScheduleClassEntity> getScheduleClassByUserId(String userId) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String hql = "FROM ScheduleClassEntity s " +
                    "JOIN FETCH s.course " +
                    "JOIN FETCH s.subject " +
                    "JOIN FETCH s.lecturer l " +
                    "WHERE l.userId = :userId " +
                    "AND s.status = :status ";

            Query<ScheduleClassEntity> query = session.createQuery(hql, ScheduleClassEntity.class);
            query.setParameter("userId", userId);
            query.setParameter("status", "scheduled");
            ArrayList<ScheduleClassEntity> scheduleClassList = (ArrayList)query.getResultList();

            return scheduleClassList;

        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
