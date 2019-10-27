package com.cyl.hibernate;

import com.cyl.model.Order;
import com.cyl.model.User;
import com.cyl.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.Date;

/**
 * @author : Liu
 * @Date : 2019/10/25 下午 05:27
 * @Description :
 */

public class HibernateTest {

    @Test
    public  void saveTest(){
        //hibernate要求操作需求要有事務的控制
        //1.獲取sessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();
        //2.創建session對象
        //session 封裝connection,Hibernate提供給我們操作資料庫的一個API
        Session session = factory.openSession();
        //3.開啟事務
        session.beginTransaction();
        //4.創建物件
        User user = new User();
        user.setUsername("java1806");
        user.setPassword("666666");
        user.setBirthday(new Date());
        //5.保存對象
        //新插入紀錄的id
        int result = (Integer) session.save(user);
        System.out.println(result);
        //6.提交事務
        session.getTransaction().commit();
        //7.關閉資源
        session.close();
        factory.close();
    }

    @Test
    public  void saveOrderTest(){
        //hibernate要求操作需求要有事務的控制
        //1.獲取sessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();
        //2.創建session對象
        //session 封裝connection,Hibernate提供給我們操作資料庫的一個API
        Session session = factory.openSession();
        //3.開啟事務
        session.beginTransaction();
        //4.創建物件
        Order order = new Order();
        order.setOrderMoney(123);
        order.setOrderNo("20191026");
        //5.保存對象
        session.save(order);
        //6.提交事務
        session.getTransaction().commit();
        //7.關閉資源
        session.close();
        factory.close();
    }

    @Test
    public void delTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        //業務
        User user = new User();
        //核心點 id
        //try catch
        user.setId(1);
        session.delete(user);

        //
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Test
    public void getTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //業務
        //核心點 id
        User user = (User) session.get(User.class,2);
        System.out.println(user.getUsername());
        //
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Test
    public void updateTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        //業務
        //沒賦值的屬性,會被賦值為null,因此使用先get再賦值
        User user = (User) session.get(User.class,2);
        user.setPassword("88888");
        session.update(user);

        //
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Test
    public void delWithTryCatchTest(){
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            session.beginTransaction();
            //業務
            User user = new User();
            //核心點 id
            //try catch
            user.setId(1);
            session.delete(user);
            //
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            if (session != null){
                session.close();
            }
            if (factory != null){
                factory.close();
            }
        }

    }

//    get vs load
//    相同點,都會使用緩存

    @Test
    public void cacheTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        //查詢緩存
        //核心點 id
        User user1 = (User) session.get(User.class,2);//sql
        //清空緩存
//        session.clear();
//        User user2 = (User) session.load(User.class,2);


        //
        session.getTransaction().commit();
        System.out.println(user1.getUsername());
//        System.out.println(user2.getUsername());
        session.close();
        factory.close();
    }

    @Test
    public void sessionTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session1 = factory.openSession();
        Session session2 = factory.openSession();

        session1.close();
        factory.close();
    }
}
