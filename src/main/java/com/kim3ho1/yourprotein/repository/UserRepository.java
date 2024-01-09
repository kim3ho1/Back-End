package com.kim3ho1.yourprotein.repository;

import com.kim3ho1.yourprotein.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);

    Optional<User> findByKakaoId(Long kakaoId);
}
