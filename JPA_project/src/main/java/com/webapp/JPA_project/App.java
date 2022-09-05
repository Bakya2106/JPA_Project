package com.webapp.JPA_project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args )
    {
       EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaproject");
       EntityManager em=emf.createEntityManager();
       
      // Person p=em.find(Person.class, 4);
       // System.out.println(p);
       em.getTransaction().begin();
       Person p=new Person();
       p.setS_no(100);
       p.setFirstname("Bakya");
       p.setLastname("Pandiarajan");
       em.persist(p);
       em.getTransaction().commit();
       em.close();
    }
}
