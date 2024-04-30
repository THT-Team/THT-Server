package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
}
