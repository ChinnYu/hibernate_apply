package com.cyl.hibernate;

import com.cyl.model.Teacher;
import com.cyl.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @author : Liu
 * @Date : 2019/10/31 下午 06:02
 * @Description :
 */

public class CriteriaTest {

    @Test
    public void list(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Criteria criteria = session.createCriteria(Teacher.class);
        List<Teacher> list = criteria.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getName());
        }
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void page(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Criteria criteria = session.createCriteria(Teacher.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(1);
        List<Teacher> list = criteria.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getName());
        }
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void order(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Criteria criteria = session.createCriteria(Teacher.class);
        Order order = Order.desc("id");
        criteria.addOrder(order);
        criteria.setFirstResult(2);
        criteria.setMaxResults(2);
        List<Teacher> list = criteria.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId()+"->"+teacher.getName());
        }
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void whereTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Criteria criteria = session.createCriteria(Teacher.class);
        Criterion criterion = Restrictions.between("id",1,20); //id between 1 and 2
        criteria.add(criterion);
        Order order = Order.desc("id");
        criteria.addOrder(order);
        criteria.setFirstResult(2);
        criteria.setMaxResults(2);
        List<Teacher> list = criteria.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId()+"->"+teacher.getName());
        }
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void likeTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Criteria criteria = session.createCriteria(Teacher.class);
        Criterion criterion1 = Restrictions.between("id",1,20);
        Criterion criterion = Restrictions.like("name","Le%");
        criteria.add(criterion1);
        criteria.add(criterion);
        Order order = Order.desc("id");
        criteria.addOrder(order);
        criteria.setFirstResult(0);
        criteria.setMaxResults(2);
        List<Teacher> list = criteria.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId()+"->"+teacher.getName());
        }
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void SQLTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //

        session.createSQLQuery("真正的SQL");
        //
        session.getTransaction().commit();
        factory.close();
    }
}
