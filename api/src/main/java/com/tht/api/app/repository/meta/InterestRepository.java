package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.Interest;
import com.tht.api.app.facade.interest.response.InterestResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

    List<InterestResponse> findAllBy();
}
