package com.drmarkdown.auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity(name = "users")
@EqualsAndHashCode(callSuper = true)
public class MarkdownUserModel extends GenericModel {

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String displayName;

    @Column(unique = true)
    private String email;

    @Column
    private String jwtToken;

    @Column
    private String password;

    @ElementCollection
    private List<String> roles;

    public MarkdownUserModel() {
        super();
    }
}
