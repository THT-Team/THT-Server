package com.tht.infra.talkkeyword;

import com.tht.infra.EntityState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkKeywordRepository extends JpaRepository<TalkKeyword, Integer> {

    public List<TalkKeyword> findAllByState(final EntityState state);
}
