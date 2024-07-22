package com.tht.domain.entity.talkkeyword;

import com.tht.enums.EntityState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkKeywordRepository extends JpaRepository<TalkKeyword, Integer> {

    List<TalkKeyword> findAllByState(final EntityState state);
}
