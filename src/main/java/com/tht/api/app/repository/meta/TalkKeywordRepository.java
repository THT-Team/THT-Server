package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.entity.meta.TalkKeyword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkKeywordRepository extends JpaRepository<TalkKeyword, Integer> {

    public List<TalkKeyword> findAllByState(final EntityState state);
}
