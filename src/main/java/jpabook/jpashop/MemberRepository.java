package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링 컨테이너에서 알아서 이 어노테이션이 있으면 엔티티 매니저를 알아서 부여해준다
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 로직을 많이 섞으면 사이드 이펙트가 있어서 좋지 않지만 간단하게 id 정도 리턴하면 편리함
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
