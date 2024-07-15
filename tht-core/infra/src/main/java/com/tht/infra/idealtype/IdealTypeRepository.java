package com.tht.infra.idealtype;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdealTypeRepository extends JpaRepository<IdealType, Integer> {

    List<IdealType> findAllByIdxIn(final List<Integer> idealTypeList);
}
