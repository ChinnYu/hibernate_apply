package com.cyl.hibernate;

import com.cyl.model.Classes;
import com.cyl.model.Teacher;
import com.cyl.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

/**
 * @author : Liu
 * @Date : 2019/10/31 下午 10:07
 * @Description :
 */

public class CacheTest {

    @Test
    public void firstLevelCacheTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Teacher teacher = session.get(Teacher.class,1);
        Teacher teacher1 = session.get(Teacher.class,1);
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void secondLevelCacheTest(){
        //一級緩存:session級別的緩存,有效區域就是當前session,如果session被關閉了,那麼後續獲取重複的數據,也依然會重發送sql
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Teacher teacher = session.get(Teacher.class,1);
        session.getTransaction().commit();
        session = factory.getCurrentSession();
        session.beginTransaction();
        Teacher teacher1 = session.get(Teacher.class,1);
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void secondLevelCacheTest2(){
        //一級緩存:session級別的緩存,有效區域就是當前session,如果session被關閉了,那麼後續獲取重複的數據,也依然會重發送sql
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Classes classes = session.get(Classes.class,1);
        session.getTransaction().commit();
        session = factory.getCurrentSession();
        session.beginTransaction();
        Classes classes2 = session.get(Classes.class,1);
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void secondLevelCacheTest3(){
        //一級緩存:session級別的緩存,有效區域就是當前session,如果session被關閉了,那麼後續獲取重複的數據,也依然會重發送sql
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Classes classes = session.get(Classes.class,1);
        System.out.println(classes.getTeachers().size());
        session.getTransaction().commit();
        session = factory.getCurrentSession();
        session.beginTransaction();
        Classes classes2 = session.get(Classes.class,1);
        System.out.println(classes2.getTeachers().size());
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void HQLTest4(){

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Query query = session.createQuery("from Teacher where id=:id");
        query.setInteger("id",1);
        //使用查詢緩存
//        query.setCacheable(true);
        Teacher teacher = (Teacher) query.uniqueResult();
        session.getTransaction().commit();
        session = factory.getCurrentSession();
        session.beginTransaction();
        query = session.createQuery("from Teacher where id=:id");
        query.setInteger("id",1);
        query.setCacheable(true);
        Teacher teacher1 = (Teacher) query.uniqueResult();

        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getDiskPath(){
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);
    }
}
