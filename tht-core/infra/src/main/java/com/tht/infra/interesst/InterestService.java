package com.tht.infra.interesst;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestService {

    private final InterestRepository interestRepository;

    public List<Interest> findAllList() {
        return interestRepository.findAll();
    }

    public void existIn(final List<Integer> interestList) {

        List<Interest> list = interestRepository.findAllByIdxIn(interestList);

        if (list.size() != interestList.size()) {
            throw new IllegalArgumentException(
                interestList.size() + "의 관심사를 요청하였지만, " + list.size() + "개가 조회됩니다.");
        }
    }
}
