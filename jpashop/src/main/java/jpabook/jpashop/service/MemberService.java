package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기전용이니까 Transaction시 DB에만 요청하도록, 성능향상
@RequiredArgsConstructor // final인 필드들만 생성자를 생성해줌
public class MemberService {
    // 필드위에 Autowired만 설정해도 좋지만 이후 테스트나 조립 클래스 변경 시 번거로움이 있음
    // @RequiredArgsConstructor를 통해 테스트 쉽게 할 수 있음
    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     **/
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);

        // Id는 GeneratedValue여서 영솏겅 컨텍스트에 등록할 떄 Id를 만들어줌
        return member.getId();
    }

    /**
     * 중복 회원 검증 메서드
     **/
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     **/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

     public Member findMember(Long memberId){
         return memberRepository.findOne(memberId);
     }

}
