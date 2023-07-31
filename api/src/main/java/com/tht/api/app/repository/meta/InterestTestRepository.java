package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.InterestTest;
import com.tht.api.app.facade.interestTest.response.InterestTestResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestTestRepository extends JpaRepository<InterestTest, Integer> {
    List<InterestTestResponse> findAllBy();
}
