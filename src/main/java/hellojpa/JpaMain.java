package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //DB와 연결 설정, 하나의 트랜잭션을 수행할 때마다 반드시 만들어 주어고 닫아줘야 한다. (사용하고 버리는것 처럼) -> 쓰레드간 공유X
        //persistence.xml 에서 <persistence-unit name="hello"> 으로 선언 했던 것을 매개변수로 넣는다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //고객의 DB 요청을 다루는 매니저 -> 이 매니저를 통해 DB의 CRUD 를 한다.
        EntityManager em = emf.createEntityManager();

        //트랜잭션 생성
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            //준영속상태 => JPA 에서 관리하지 않는것
            //특정 엔티티만 준영속상태로 전환
            em.detach(member);

            System.out.println("==========================");
            Member member2 = em.find(Member.class, 150L);

            tx.commit();
            /** Insert
            Member member = new Member();
            member.setId(2L);
            member.setName("김윤정");

            em.persist(member);
            **/
            /** Select
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            tx.commit();
             **/
            /** Delete
             Member findMemeber = em.find(Member.class, 1L);
             em.remove(findMemeber);
             tx.commit();
             **/
            /** Update
            Member findMemeber = em.find(Member.class, 1L);
            findMemeber.setName("가브송");
            tx.commit();
             **/
            /**
            //JPQL : 엔티티 객체를 대상으로 쿼리를 짠다. || SQL: 데이터베이스 테이블을 대상으로 쿼리를 짠다.
            //Member 객체를 대상으로 쿼리를 짜야한다. (Member 객체를 다 가져와서 객체 m 으로 보여준다.)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.mname = " + member.getName());
            }
            **/
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();

        }
        emf.close();
        
    }
}
