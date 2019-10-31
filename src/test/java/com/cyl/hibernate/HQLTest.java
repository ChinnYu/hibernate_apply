package com.cyl.hibernate;

import com.cyl.model.Order;
import com.cyl.model.Teacher;
import com.cyl.model.User;
import com.cyl.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @author : Liu
 * @Date : 2019/10/30 下午 10:52
 * @Description :
 */

public class HQLTest {

    @Test
    public void listTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Query query = session.createQuery("from Teacher");
        List<Teacher> list = query.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId());
        }
        //
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSingleTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //
        Query query = session.createQuery("from Teacher where id=1");
        Teacher teacher = (Teacher) query.getSingleResult();
        System.out.println(teacher.getName());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSingleTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t from Teacher t where t.id=1");
        Teacher teacher = (Teacher) query.getSingleResult();
        System.out.println(teacher.getName());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSubPartTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t.name from Teacher t where t.id=1");
        String name = (String) query.getSingleResult();
        System.out.println(name);
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSubPartTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t.id,t.name from Teacher t where t.id=1");
        Object[] objects = (Object[]) query.getSingleResult();
        for (Object object : objects){
            System.out.println(object);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSubPartTest3(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t.name from Teacher t");
        List<Object> list=  query.list();
        for (Object object : list){
            System.out.println(object);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSubPartTest4(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t.id,t.name from Teacher t");
        List<Object[]> list=  query.list();
        for (Object[] object : list){
            System.out.println(object[0]);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void getSubPartTest5(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select new Teacher(t.id,t.name) from Teacher t where t.id=1");
        Teacher teacher = (Teacher) query.getSingleResult();
        System.out.println(teacher.getId());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void orderTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t from Teacher t order by t.id desc ");
        List<Teacher> list = query.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId());
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void pageTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t from Teacher t ");
        //設置分頁
        //startIndex,pageSize
        query.setFirstResult(0);//startIndex
        query.setMaxResults(2);//pageSize
        List<Teacher> list = query.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId());
        }

        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void setParamTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t from Teacher t where t.id=?");
        //設置參數值
        //下標 ,值
        query.setInteger(0,1);
        Teacher teacher = (Teacher) query.uniqueResult();
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void setParamTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select t from Teacher t where t.id=:id");
        //設置參數值
        //下標字符串 ,值
        query.setInteger("id",20);
        Teacher teacher = (Teacher) query.uniqueResult();
        System.out.println(teacher.getId());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void countTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select count(*) from Teacher");

        Long count = (Long) query.uniqueResult();

        System.out.println(count.intValue());
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void sumTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select sum(o.orderMoney) from Order o ");
        Long sum = (Long) query.uniqueResult();
        System.out.println(sum);
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void groupByTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("select o.user.id, sum(o.orderMoney) from Order o group by o.user.id");
        List<Object[]> list = query.list();
        for (Object[] objects : list){
            System.out.println(objects[0]+"->"+objects[1]);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void joinTest1(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("from Order o,User u");
        List<Object[]> list = query.list();
        for (Object[] objects : list){
            System.out.println(objects[0]+"->"+objects[1]);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void joinTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("from Order o inner join o.user u");
        List<Object[]> list = query.list();
        for (Object[] objects : list){
            System.out.println(objects[0]+"->"+objects[1]);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void joinTest3(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("from User u inner join u.orders ");
        List<Object[]> list = query.list();
        for (Object[] objects : list){
            User user = (User) objects[0];
            Order order = (Order) objects[1];
            System.out.println(user.getId()+"->"+user.getUsername());
            System.out.println(order.getId()+"->"+order.getOrderMoney());
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void joinTest4(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("from User u inner join fetch u.orders ");
        List<User> list = query.list();
        for (User user : list){
            System.out.println(user.getId()+"->"+user.getUsername());
            System.out.println(user.getOrders().size());
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void joinTest5(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        Query query = session.createQuery("from Order o inner join fetch o.user where o.id>3");
        List<Order> list = query.list();
        for (Order order : list){
//            System.out.println(order.getId()+"->"+order.getOrderMoney());
//            System.out.println(order.getUser().getUsername());
            System.out.println(order);
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void nameQueryTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        //全局
        Query query = session.getNamedQuery("wholeListTeacher");
        List<Teacher> list = query.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId()+"->"+teacher.getName());
        }
        session.getTransaction().commit();
        factory.close();
    }

    @Test
    public void nameQueryTest2(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        //select * from teacher where id =1;
        //局部
        Query query = session.getNamedQuery("com.cyl.model.Teacher.listTeacher");
        List<Teacher> list = query.list();
        for (Teacher teacher : list){
            System.out.println(teacher.getId()+"->"+teacher.getName());
        }
        session.getTransaction().commit();
        factory.close();
    }
}
