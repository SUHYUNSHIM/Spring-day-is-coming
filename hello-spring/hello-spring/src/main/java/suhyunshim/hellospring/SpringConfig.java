package suhyunshim.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import suhyunshim.hellospring.aop.TimeTraceAop;
import suhyunshim.hellospring.domain.Member;
import suhyunshim.hellospring.repository.JpaMemberRepository;
import suhyunshim.hellospring.repository.MemberRepository;
import suhyunshim.hellospring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //private final DataSource dataSource;
    private EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*@Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
*/

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
