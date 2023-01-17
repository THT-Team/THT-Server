package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.IdealType;
import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdealTypeRepository extends JpaRepository<IdealType, Integer> {

    List<IdealTypeResponse> findAllBy();
}
