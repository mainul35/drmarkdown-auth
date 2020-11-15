package com.drmarkdown.auth.repositories;

import com.drmarkdown.auth.models.MarkdownUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MarkdownUserModel, String> {

    Optional<MarkdownUserModel> findByUsername(String username);

    Optional<MarkdownUserModel> findByJwtToken(String jwtToken);

//    List<MarkdownUserModel> findByDisplayNameOrUsernameOrEmail(String username);
}
