package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.EnrollmentDao;
import edu.self.sams.entity.*;
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
        return null;
    }

    @Override
    public ArrayList<EnrollmentEntity> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<TblEnrollmentEntity> getAllEnrollments() throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "SELECT e.student.regNo, e.student.name, e.course.courseCode, e.course.courseName, e.batch, e.status " +
                        "FROM EnrollmentEntity e " +
                        "ORDER BY e.batch DESC, e.student.regNo";
            
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            ArrayList<Object[]> results = (ArrayList<Object[]>) query.getResultList();
            
            ArrayList<TblEnrollmentEntity> enrollmentList = new ArrayList<>();
            for (Object[] row : results) {
                TblEnrollmentEntity tblEnrollment = new TblEnrollmentEntity((String) row[0], (String) row[1], (String) row[2], (String) row[3], (Integer) row[4], (String) row[5]);
                enrollmentList.add(tblEnrollment);
            }
            return enrollmentList;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<TblEnrollmentEntity> findEnrollmentsByCourseCode(String courseCode) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT e.student.regNo, e.student.name, e.course.courseCode, e.course.courseName, e.batch, e.status " +
                    "FROM EnrollmentEntity e " +
                    "WHERE e.course.courseCode = :courseCode " +
                    "ORDER BY e.batch DESC, e.student.regNo";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("courseCode", courseCode);
            ArrayList<Object[]> results = (ArrayList<Object[]>) query.getResultList();

            ArrayList<TblEnrollmentEntity> enrollmentList = new ArrayList<>();
            for (Object[] row : results) {
                TblEnrollmentEntity tblEnrollment = new TblEnrollmentEntity((String) row[0], (String) row[1], (String) row[2], (String) row[3], (Integer) row[4], (String) row[5]);
                enrollmentList.add(tblEnrollment);
            }
            return enrollmentList;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<TblEnrollmentEntity> findEnrollmentsByStudentRegNo(String studentRegNo) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT e.student.regNo, e.student.name, e.course.courseCode, e.course.courseName, e.batch, e.status " +
                    "FROM EnrollmentEntity e " +
                    "WHERE e.student.regNo = :regNo " +
                    "ORDER BY e.batch DESC, e.student.regNo";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("regNo", studentRegNo);
            ArrayList<Object[]> results = (ArrayList<Object[]>) query.getResultList();

            ArrayList<TblEnrollmentEntity> enrollmentList = new ArrayList<>();
            for (Object[] row : results) {
                TblEnrollmentEntity tblEnrollment = new TblEnrollmentEntity((String) row[0], (String) row[1], (String) row[2], (String) row[3], (Integer) row[4], (String) row[5]);
                enrollmentList.add(tblEnrollment);
            }
            return enrollmentList;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
