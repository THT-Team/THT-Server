package com.tht.api.app.repository;


import com.tht.api.app.entity.user.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestsRepository extends JpaRepository<UserInterests, Long> {

}
