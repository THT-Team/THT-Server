package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.Interest;
import com.tht.api.app.facade.response.InterestResponse;
import io.hypersistence.utils.spring.repository.HibernateRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends HibernateRepository<Interest>, JpaRepository<Interest, Integer> {

    List<InterestResponse> findAllBy();
}
