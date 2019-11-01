package com.cyl.hibernate;

import com.cyl.model.Teacher;
import com.cyl.model.User;
import com.cyl.util.HibernateUtil;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

/**
 * @author : Liu
 * @Date : 2019/11/1 下午 06:14
 * @Description :
 */

public class LockTest {


    @Test
    public void pessLockTest(){
        //悲觀鎖,通過資料庫提供的鎖機制來實現的,方式就是在語句後面加上for update
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Query query = session.createQuery("from Teacher where id=1");
        LockOptions lockOption = LockOptions.UPGRADE;
        query.setLockOptions(lockOption);
        Teacher teacher = (Teacher) query.uniqueResult();
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void optimisticLockTest(){
        //悲觀鎖,通過資料庫提供的鎖機制來實現的,方式就是在語句後面加上for update
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Teacher teacher = session.get(Teacher.class,1);
        teacher.setName("jason");
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void optimisticLockTest2(){
        //悲觀鎖,通過資料庫提供的鎖機制來實現的,方式就是在語句後面加上for update
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        User user = session.get(User.class,2);
        user.setUsername("jason");
        //
        session.getTransaction().commit();
        factory.close();
    }
}
