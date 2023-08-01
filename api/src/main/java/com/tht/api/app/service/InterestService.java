package com.tht.api.app.service;

import com.tht.api.app.entity.meta.Interest;
import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.repository.meta.InterestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestService {

    private final InterestRepository interestRepository;

    public List<InterestResponse> findAllList() {
        return interestRepository.findAllBy();
    }

    public void existIn(final List<Integer> interestList) {

        List<Interest> list = interestRepository.findAllByIdxIn(interestList);

        if (list.size() != interestList.size()) {
            throw new IllegalArgumentException(
                interestList.size() + "의 관심사를 요청하였지만, " + list.size() + "개가 조회됩니다.");
        }
    }
}
