package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.custom.UserDao;
import edu.self.sams.entity.UserEntity;
import edu.self.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(UserEntity userEntity) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public boolean update(UserEntity userEntity) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(userEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public boolean delete(String userId) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            UserEntity userEntity = session.find(UserEntity.class, userId);
            if (userEntity != null) {
                session.remove(userEntity);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public UserEntity get(String userId) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(UserEntity.class, userId);
        }
    }

    @Override
    public ArrayList<UserEntity> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<UserEntity> query = session.createQuery("FROM UserEntity", UserEntity.class);
            return new ArrayList<>(query.list());
        }
    }
}
