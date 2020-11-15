package com.drmarkdown.auth.repositories;

import com.drmarkdown.auth.models.MarkdownRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<MarkdownRoleModel, String> {

    Optional<MarkdownRoleModel> findById(String id);
}
