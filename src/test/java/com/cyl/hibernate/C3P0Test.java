package com.cyl.hibernate;

import com.cyl.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Liu
 * @Date : 2019/11/1 下午 05:38
 * @Description :
 */

public class C3P0Test {

    @Test
    public void checkTest(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                System.out.println(connection);
            }
        });
        //檢驗是否真的是C3P0提供的連接資源
//        Connection connection = session.connection() 沒用;
        //
        session.getTransaction().commit();
        factory.close();
    }
}
