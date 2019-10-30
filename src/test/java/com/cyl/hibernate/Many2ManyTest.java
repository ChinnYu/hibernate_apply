package com.cyl.hibernate;

import com.cyl.model.Classes;
import com.cyl.model.Teacher;
import com.cyl.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Liu
 * @Date : 2019/10/29 上午 10:52
 * @Description :
 */

public class Many2ManyTest {
    @Test
    public void saveTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Teacher t1 = new Teacher();
        t1.setName("Yang1");

        Teacher t2 = new Teacher();
        t2.setName("Yang2");

        Classes c1 = new Classes();
        c1.setName("java1801");

        Classes c2 = new Classes();
        c2.setName("java1802");

        //建立關係
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.add(t1);
        teachers.add(t2);

        Set<Classes> classesSet = new HashSet<Classes>();
        classesSet.add(c1);
        classesSet.add(c2);

        t1.setClassesSet(classesSet);
        t2.setClassesSet(classesSet);

        c1.setTeachers(teachers);
        c2.setTeachers(teachers);

        //保存
        session.save(t1);
//        session.save(t2);
//        session.save(c1);
//        session.save(c2);
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void delTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Teacher teacher = session.get(Teacher.class,13);
        session.delete(teacher);
        //保存

        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void delTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Teacher teacher = session.get(Teacher.class,17);
        Set<Classes> classesSet = teacher.getClassesSet();
        for (Classes classes : classesSet){
            //classes.setTeachers(null);
            classes.getTeachers().remove(teacher);
        }
        teacher.setClassesSet(null);
        session.delete(teacher);
        //保存

        session.getTransaction().commit();
        factory.close();
    }
}
