package com.tht.domain.entity.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<UserInquiry, Long> {
}
