package com.abhijith.gymapp.repository;

import com.abhijith.gymapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Modifying
    @Query(value ="delete from users d where d.username= :username", nativeQuery = true)
    @Transactional
    public void deleteById(@Param("username") String un);
}
