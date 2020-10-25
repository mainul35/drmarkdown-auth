package com.drmarkdown.auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "roles")
@EqualsAndHashCode(callSuper = true)
public class MarkdownRoleModel extends GenericModel {
    private String role;

    public MarkdownRoleModel() {
        super();
    }
}
