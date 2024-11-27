package com.jnu.mcd.ddobagi.domains.help.persistence;

import com.jnu.mcd.ddobagi.common.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
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
        name = HelpMapping.ENTITY_PREFIX
)
@SQLDelete(sql = "UPDATE help_mapping SET is_deleted=true WHERE help_mapping_id=?")
@Where(clause = "is_deleted=false")
public class HelpMapping extends BaseEntity {

    public static final String ENTITY_PREFIX = "help_mapping";

    @EmbeddedId
    private HelpMappingId id;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    @Embeddable
    public static class HelpMappingId {

        @Column(name = "help_id", nullable = false)
        private Long helpId;
        @Column(name = "member_id", nullable = false)
        private Long memberId;

    }

}
