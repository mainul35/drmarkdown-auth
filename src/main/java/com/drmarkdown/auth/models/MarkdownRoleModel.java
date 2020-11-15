package com.drmarkdown.auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class MarkdownRoleModel extends GenericModel {

    @Column
    private String role;

    public MarkdownRoleModel() {
        super();
    }
}
