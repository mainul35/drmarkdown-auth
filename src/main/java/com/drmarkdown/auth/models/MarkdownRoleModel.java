package com.drmarkdown.auth.models;

import com.drmarkdown.auth.dtos.RoleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class MarkdownRoleModel extends GenericModel {

    @Column
    private String role;

    public MarkdownRoleModel() {
        super();
    }

    public MarkdownRoleModel(RoleDto roleDto) {
        this.setId(roleDto.getId() == null ? UUID.randomUUID().toString() : roleDto.getId());
        this.setRole(roleDto.getRole());
    }
}
