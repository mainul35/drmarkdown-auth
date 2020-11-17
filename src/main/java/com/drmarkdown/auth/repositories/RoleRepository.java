package com.drmarkdown.auth.repositories;

import com.drmarkdown.auth.models.MarkdownRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RoleRepository extends JpaRepository<MarkdownRoleModel, String> {

    Optional<MarkdownRoleModel> findById(String id);

    Optional<MarkdownRoleModel> findByRole(String role);
}
