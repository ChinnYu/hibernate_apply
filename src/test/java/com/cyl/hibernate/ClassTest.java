package com.cyl.hibernate;

import com.cyl.model.Classes;
import com.cyl.model.Teacher;
import com.cyl.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author : Liu
 * @Date : 2019/10/30 下午 02:54
 * @Description :
 */

public class ClassTest {

    @Test
    public void getTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class,205);
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void lazyTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class,20);
        Set<Classes> classesSet = teacher.getClassesSet();
//        Hibernate.initialize(teacher);
        //默認情況,只有你用到了集合才會給你加載
//        Set<Classes> classesSet = teacher.getClassesSet();
//        System.out.println(classesSet.size());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSet1Test(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class,20);
//        Query query = session.createQuery("from Teacher");//HQL
//        List<Teacher> list = query.list();
//        for (Teacher teacher1 : list){
////            System.out.println(teacher1.getClassesSet().size());
//            System.out.println(teacher1);
//        }
//        System.out.println(teacher.getClassesSet().size());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSetTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
//        Teacher teacher = session.get(Teacher.class,20);
        Query query = session.createQuery("from Teacher");//HQL
        List<Teacher> list = query.list();
        for (Teacher teacher1 : list){
//            System.out.println(teacher1.getClassesSet().size());
            System.out.println(teacher1);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void batchSizeSTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Teacher");//HQL
        List<Teacher> list = query.list();
        for (Teacher teacher1 : list){
            System.out.println(teacher1.getClassesSet().size());
//            System.out.println(teacher1);
        }
        session.getTransaction().commit();
        factory.close();
    }
}
