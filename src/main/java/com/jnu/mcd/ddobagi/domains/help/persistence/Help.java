package com.jnu.mcd.ddobagi.domains.help.persistence;


import com.jnu.mcd.ddobagi.common.persistence.BaseEntity;
import com.jnu.mcd.ddobagi.domains.help.application.model.HelpStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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

    @Column(name = ENTITY_PREFIX + "_status", nullable = false)
    @Builder.Default
    private HelpStatus helpStatus = HelpStatus.NON_MATCH;

}
