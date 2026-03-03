package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.EnrollmentDao;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.entity.EnrollmentEntity;
import edu.self.sams.entity.EnrollmentId;
import edu.self.sams.entity.StudentEntity;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class EnrollmentDaoImpl implements EnrollmentDao {
    
    @Override
    public boolean save(EnrollmentEntity enrollmentEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CourseEntity course = session.find(CourseEntity.class, enrollmentEntity.getEnrollmentId().getCourseCode());
            StudentEntity student = session.find(StudentEntity.class, enrollmentEntity.getEnrollmentId().getRegNo());
            
            if (course == null) {
                throw new Exception("Course not found with code: " + enrollmentEntity.getEnrollmentId().getCourseCode());
            }
            if (student == null) {
                throw new Exception("Student not found with reg no: " + enrollmentEntity.getEnrollmentId().getRegNo());
            }

            enrollmentEntity.setCourse(course);
            enrollmentEntity.setStudent(student);
            
            session.persist(enrollmentEntity);
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
    public boolean update(EnrollmentEntity enrollmentEntity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CourseEntity course = session.find(CourseEntity.class, enrollmentEntity.getEnrollmentId().getCourseCode());
            StudentEntity student = session.find(StudentEntity.class, enrollmentEntity.getEnrollmentId().getRegNo());
            
            if (course == null) {
                throw new Exception("Course not found with code: " + enrollmentEntity.getEnrollmentId().getCourseCode());
            }
            if (student == null) {
                throw new Exception("Student not found with reg no: " + enrollmentEntity.getEnrollmentId().getRegNo());
            }

            enrollmentEntity.setCourse(course);
            enrollmentEntity.setStudent(student);
            
            session.merge(enrollmentEntity);
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
    public boolean delete(EnrollmentId enrollmentId) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            EnrollmentEntity enrollmentEntity = session.find(EnrollmentEntity.class, enrollmentId);
            if (enrollmentEntity != null) {
                session.remove(enrollmentEntity);
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
    public EnrollmentEntity get(EnrollmentId enrollmentId) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.find(EnrollmentEntity.class, enrollmentId);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<EnrollmentEntity> getAll() throws Exception {
        return null;
    }
}
