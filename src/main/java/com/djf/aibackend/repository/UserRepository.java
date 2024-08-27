package com.djf.aibackend.repository;

import com.djf.aibackend.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserData, Integer> {
     UserData findByAndroidId(String androidId);
}
