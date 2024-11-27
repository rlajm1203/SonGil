package com.jnu.mcd.ddobagi.domains.member.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT m FROM Member m WHERE m.loginId=:loginId")
    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Query("SELECT m FROM Member m ORDER BY m.memberId ASC ")
    List<Member> findAllAsc();

    @Query("SELECT m FROM Member m WHERE m.memberId=:memberId")
    Optional<Member> findByMemberId(@Param("memberId") Long memberId);
}
