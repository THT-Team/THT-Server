package com.tht.api.app.service;

import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.entity.meta.TalkKeyword;
import com.tht.api.app.repository.meta.TalkKeywordRepository;
import java.util.List;
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
