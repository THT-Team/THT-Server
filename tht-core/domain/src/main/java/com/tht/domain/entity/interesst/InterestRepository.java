package com.tht.domain.entity.interesst;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

    List<Interest> findAllByIdxIn(List<Integer> idxList);
}
