package suhyunshim.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suhyunshim.hellospring.domain.Member;
import suhyunshim.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service //수정한 부분
public class MemberService {

   /* private final MemberRepository memberRepository;

    //@Autowired //수정한 부분
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/
   private final MemberRepository memberRepository;
   //@Autowired //수정한 부분
   public MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
   }
    /*
    회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x

        //validateDuplicateMember(member);
        /*Optional<Member> result =memberRepository.findByName(member.getName());
        /result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        */
        //validateDuplicateMember(member); //중복 회원 검증
        long start = System.currentTimeMillis();
        try{
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join"+timeMs +" ms");
        }
        /*memberRepository.save(member);
        return member.getId();*/
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    전체 회원 조회
    * */
    public List<Member> findMembers(){
        //return memberRepository.findAll();
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
            }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers "+timeMs +"ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
