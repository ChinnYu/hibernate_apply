package com.cyl.hibernate;

import com.cyl.model.Order;
import com.cyl.model.User;
import com.cyl.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Liu
 * @Date : 2019/10/27 下午 06:14
 * @Description :
 */

public class Many2OneTest {
    @Test
    public void saveTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        User user = new User();
        user.setUsername("xiao");
        user.setPassword("66666");
        user.setBirthday(new Date());

        Order order1 = new Order();
        order1.setOrderNo("2019555");
        order1.setOrderMoney(10000);

        Order order2 = new Order();
        order2.setOrderNo("201955556");
        order2.setOrderMoney(20000);

        //建立他們之間的關係
        order1.setUser(user);
        order2.setUser(user);

        //保存
        session.save(user);
        session.save(order1);
        session.save(order2);
        System.out.println(user.getId());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void one2ManyTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        User user = new User();
        user.setUsername("xiaoma4");
        user.setPassword("66666");
        user.setBirthday(new Date());

        Order order1 = new Order();
        order1.setOrderNo("2019555");
        order1.setOrderMoney(10000);

        Order order2 = new Order();
        order2.setOrderNo("201955556");
        order2.setOrderMoney(20000);

        //建立他們之間的關係
//        user-->orders
        order1.setUser(user);
        order2.setUser(user);
        Set<Order> orders = new HashSet<Order>();
        orders.add(order1);
        orders.add(order2);
        user.setOrders(orders);
        //保存

        session.save(user);
//        session.save(order1);
//        session.save(order2);
        System.out.println(user.getId());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void delTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        User user = (User) session.get(User.class, 24);
        session.delete(user);
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void delTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        User user = (User) session.get(User.class, 24);
        Set<Order> orders = user.getOrders();
        for (Order order : orders){
            order.setUser(null);
        }
        user.setOrders(null);
        session.delete(user);
        //
        session.getTransaction().commit();
        factory.close();
    }
}
