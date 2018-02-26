package com.zc.cris.hebernate.pojo;


import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class HibernateTest {

	
	@Test
	void test() {
		//1. 创建一个SessionFactory对象
		SessionFactory sessionFactory = null;
		
		//1.1 创建configuration对象:解析hibernate 的基本配置信息和对象映射关系
		//Configuration configuration = new Configuration().configure();
		
		//4.0 之前这么创建
//		sessionFactory = configuration.buildSessionFactory();
		
		//4.x 需要创建一个 ServiceRegistry 对象：hibernate的任何配置和服务都需要在该对象中注册后才能有效
//      ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
									//		.buildServiceRegistry();
		//创建我们的数据库访问会话工厂
//      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		 //但在5.1.0版本汇总，hibernate则采用如下新方式获取：
	    //1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
	    //在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件
	    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
	    //2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成该应用唯一（一般情况下）的一个session工厂
	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		
		//2. 创建一个Session对象
	    Session session = sessionFactory.openSession();
		
		//3. 开启事务
	    Transaction transaction = session.beginTransaction();
		
		//4. 执行保存操作
//		News news = new News("java", "zc", new Date(new java.util.Date().getTime()));
//		session.save(news);	
	    // 查询数据操作
	    News news = session.get(News.class, 1);
	    System.out.println(news);
		
		//5. 提交事务
		transaction.commit();
		
		//6. 关闭session
		session.close();
		
		//7. 关闭sessionFactory对象
		sessionFactory.close();
		
		//控制台打印的sql语句
		/*Hibernate: 
		    
		    create table NEWS (
		       ID integer not null auto_increment,
		        TITLE varchar(255),
		        AUTHOR varchar(255),
		        DATE date,
		        primary key (ID)
		    ) engine=MyISAM
		Hibernate: 
		    insert 
		    into
		        NEWS
		        (TITLE, AUTHOR, DATE) 
		    values
		        (?, ?, ?)
		        */
	}

}
