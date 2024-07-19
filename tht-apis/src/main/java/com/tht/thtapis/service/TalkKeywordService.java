package com.tht.thtapis.service;

import java.util.List;

import com.tht.enums.EntityState;
import com.tht.infra.talkkeyword.TalkKeyword;
import com.tht.infra.talkkeyword.TalkKeywordRepository;
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
