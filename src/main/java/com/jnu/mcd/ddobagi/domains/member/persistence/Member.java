package com.jnu.mcd.ddobagi.domains.member.persistence;


import com.jnu.mcd.ddobagi.common.persistence.BaseEntity;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberSignUpRequest;
import com.jnu.mcd.ddobagi.domains.member.application.model.MemberType;
import com.jnu.mcd.ddobagi.domains.member.application.support.EncryptHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
@Table(
        name = Member.ENTITY_PREFIX,
        indexes = {@Index(name = "idx_member_name", columnList = "member_name")})
@SQLDelete(sql = "UPDATE member SET is_deleted=true WHERE member_id=?")
@Where(clause = "is_deleted=false")
public class Member extends BaseEntity {

    public static final String ENTITY_PREFIX = "member";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ENTITY_PREFIX + "_id", nullable = false)
    private Long memberId;

    @Column(name = ENTITY_PREFIX + "_name", nullable = false)
    private String name;

    @Column(name = ENTITY_PREFIX + "_login_id", nullable = false)
    private String loginId;

    @Column(name = ENTITY_PREFIX + "_password", nullable = false)
    private String password;

    @Column(name = ENTITY_PREFIX + "_phone_number", nullable = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = ENTITY_PREFIX + "_type", nullable = false)
    private MemberType memberType;

    public static Member of(MemberSignUpRequest request){
        String password = request.getPassword();

        return Member.builder()
                .loginId(request.getId())
                .password(EncryptHelper.encrypt(password))
                .phoneNumber(request.getProtectorPhoneNumber())
                .name(request.getName())
                .memberType(MemberType.findMemberType(request.getMemberType()))
                .build();
    }

}
