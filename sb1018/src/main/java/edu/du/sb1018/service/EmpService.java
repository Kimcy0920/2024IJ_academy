package edu.du.sb1018.service;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.entity.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Service
public class EmpService { // 3교시

    @PersistenceUnit // EntityManager
    private EntityManagerFactory emf; // Factory, 트랜젝션도 사용할 수 있음.

    @PersistenceContext
    private EntityManager em; // select는 트랜젝션이 필요 없어서 단독으로 선언해 사용.

    public List<Emp> find_Emp() { // select
        TypedQuery<Emp> query = em.createQuery("SELECT e FROM Emp e", Emp.class);
        List<Emp> emps = query.getResultList();
        for (Emp emp : emps) {
            System.out.println(emps);
        }
        return emps;
    }

    public void update_Emp(Emp emp) { // update
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Emp emp1 = em.find(Emp.class, );
        emp.setEname();
        transaction.commit();
    }

    public void remove_Emp(int empno) { // delete
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Emp emp = em.find(Emp.class, empno);
        em.remove(emp);
        transaction.commit();
    }
}
