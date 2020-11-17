package com.drmarkdown.auth.repositories;

import com.drmarkdown.auth.models.MarkdownUserModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<MarkdownUserModel, String> {

    @EntityGraph(attributePaths = "roles")
        // Handles lazy loading
    Optional<MarkdownUserModel> findByUsername(String username);

    Optional<MarkdownUserModel> findByJwtToken(String jwtToken);

//    List<MarkdownUserModel> findByDisplayNameOrUsernameOrEmail(String username);
}
