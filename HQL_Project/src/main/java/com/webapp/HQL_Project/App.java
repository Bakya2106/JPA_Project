package com.webapp.HQL_Project;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
    	Configuration cfg=new org.hibernate.cfg.Configuration().configure().addAnnotatedClass(Person.class);
    	ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
    	SessionFactory sf=cfg.buildSessionFactory(sr);
    	Session s=sf.openSession();
    	
    	s.beginTransaction();
    	
//    	
//    	for(int i=1;i<=50;i++) {
//    		Person p=new Person();
//    		p.setFirstname("FName "+i);
//    		p.setLastname("Lname "+i);
//    		p.setS_no(i);
//    		s.save(p);
//    	}
    	
    	Query q1=s.createQuery("from Person where s_no>40");
    	List<Person> personlist=q1.list();
    	
    	for(Person pe:personlist) {
    		System.out.println(pe);
    	}
    	
    	Query q2=s.createQuery("from Person where s_no=12");
    	Person p=(Person) q2.uniqueResult();
    	System.out.println(p);
    	
    	Query q3=s.createQuery("select firstname,lastname from Person where s_no=21");
    	Object[] pe=(Object[]) q3.uniqueResult(); //Object Array
    	System.out.println(pe[0]+" "+pe[1]);
    	
    	Query q4=s.createQuery("select firstname,lastname,s_no from Person p where p.s_no< :b");
    	q4.setParameter("b", 10);
    	List <Object[]> per=(List<Object[]>)q4.list(); //List of Array
    
    	for(Object[] pers:per) {
    		System.out.println(pers[0] + " "+pers[1]+" "+pers[2]);
    	}
    	
    	
    	SQLQuery sql=s.createSQLQuery("select * from person where s_no=6");
    	sql.addEntity(Person.class);
    	System.out.println(sql.uniqueResult());
    	
    	
    	SQLQuery sq=s.createSQLQuery("select * from person where s_no>6"); //native query
    	sq.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
    	List persons=sq.list();
    	for(Object obj:persons) {
    		Map m=(Map) obj;
    		System.out.println(m.get("firstname")+" "+m.get("lastname"));
    	}
    	s.getTransaction().commit();
    	s.close();
    }
}
