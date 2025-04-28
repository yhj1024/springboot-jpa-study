package jpabook.jpashop;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith(SpringExtension.class) 스프링 2.1부터 @ExtendWith(SpringExtension.class) 내장하고 있어서 필요없음
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // 라이브 템플릿 생성하면 편함
    // $START$ : 시작커서, $END$ : 마지막 커서
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        // Given
        Member member = new Member();
        member.setUsername("memberA");
        // When
        Long saveId = memberRepository.save(member);

        // em.
        Member findMember = memberRepository.find(saveId);
        // Then
        Assertions.assertEquals(member.getId(), findMember.getId());
        Assertions.assertEquals(member.getUsername(), findMember.getUsername());

        // 영속성 컨텍스트 1차 캐시에 member가 있어서 true가 됨
        // 자세히 보면 그래서 select query 자체가 안 나감
        // 이 때 em.flush() em.close() 하면 영속성 컨텍스트가 clear 되어서 false가 됨
        Assertions.assertEquals(member, findMember);
        System.out.println("member : " + member);
        System.out.println("findMember : " + findMember);
        System.out.println("member == findMember : " + (member == findMember));
    }

    // 테스트 코드 실행하면 오류가 남
    // No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
    // JPA 작업은 트랜잭션 내에서 수행되어야 하는데 Transactional 어노테이션이 없으면 발생함

    // 이 후 실행은 되는데 h2 에 저장은 실제로 안됨 이유는 테스트 코드에 Transactional 이 있는 경우 스프링이 자동 롤백함
    // 이 때 @Rollback(false) 사용하면 저장이 됨

    // 참고 : 이 전에 persistence.xml 설정이나
    // LocalContainerEntityManagerFactoryBean 도 없음 스프링 부트에서 알아서 다 등록해줌


}