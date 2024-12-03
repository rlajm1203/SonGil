package com.jnu.mcd.ddobagi.domains.help.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HelpMappingRepository extends JpaRepository<HelpMapping, Long> {

    @Query("SELECT helpMapping FROM HelpMapping helpMapping WHERE helpMapping.id.memberId=:memberId")
    List<HelpMapping> findAllByMemberId(@Param("memberId") Long memberId);

}
