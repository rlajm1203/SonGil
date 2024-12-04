package com.jnu.mcd.ddobagi.domains.help.persistence;


import com.jnu.mcd.ddobagi.common.persistence.BaseEntity;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpRequest;
import com.jnu.mcd.ddobagi.domains.help.application.model.HelpCategory;
import com.jnu.mcd.ddobagi.domains.help.application.model.HelpStatus;
import com.jnu.mcd.ddobagi.domains.help.application.model.HelpType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
        name = Help.ENTITY_PREFIX,
        indexes = {@Index(name = "idx_help_title", columnList = "help_title")})
@SQLDelete(sql = "UPDATE help SET is_deleted=true WHERE help_id=?")
@Where(clause = "is_deleted=false")
public class Help extends BaseEntity {

    public static final String ENTITY_PREFIX = "help";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ENTITY_PREFIX + "_id", nullable = false)
    private Long helpId;

    @Column(name = ENTITY_PREFIX + "_title", nullable = false)
    private String title;

    @Column(name = ENTITY_PREFIX + "_content", nullable = false)
    private String content;
    
    @Column(name = ENTITY_PREFIX + "_member_id", nullable = false)
    private Long memberId;

    @Column(name = ENTITY_PREFIX + "_price", nullable = false)
    private Long price;

    @Column(name = ENTITY_PREFIX + "_address", nullable = false)
    private String address;

    @Column(name = ENTITY_PREFIX + "_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private HelpType helpType;

    @Column(name = ENTITY_PREFIX + "_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private HelpCategory category;

    @Column(name = ENTITY_PREFIX + "_status", nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HelpStatus helpStatus = HelpStatus.NON_MATCH;

    @Column(name = ENTITY_PREFIX + "_week", nullable = true)
    private String weeks;

    public static Help of(Long memberId, HelpRequest request){
        return Help.builder()
                .helpType(HelpType.fromString(request.helpType()))
                .content(request.content())
                .memberId(memberId)
                .price(request.price())
                .address(request.address())
                .weeks(weekToString(request.dayOfWeek()))
                .title(request.title())
                .category(request.category())
                .build();
    }

    private static String weekToString(Set<String> weeks){
        if(weeks == null) return null;
        return weeks.stream()
                .reduce("", (identity, week) -> identity +","+week);
    }

    public void updateStatus(String status){
        this.helpStatus = HelpStatus.fromString(status);
    }

}
