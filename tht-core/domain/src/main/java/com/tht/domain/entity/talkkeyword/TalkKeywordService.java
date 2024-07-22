package com.tht.domain.entity.talkkeyword;

import java.util.List;

import com.tht.enums.EntityState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TalkKeywordService {

    private final TalkKeywordRepository repository;

    public List<TalkKeyword> findAllActive() {
        return repository.findAllByState(EntityState.ACTIVE);
    }
}
