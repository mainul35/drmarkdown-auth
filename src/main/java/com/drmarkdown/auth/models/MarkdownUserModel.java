package com.drmarkdown.auth.models;

import com.drmarkdown.auth.dtos.UserInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "users")
@EqualsAndHashCode(callSuper = true)
public class MarkdownUserModel extends GenericModel {

    @Column(unique = true)
    private String username;

    @Column
    private String displayName;

    @Column(unique = true)
    private String email;

    @Column
    private String jwtToken;

    @Column
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles;

    public MarkdownUserModel() {
        super();
    }

    public MarkdownUserModel(UserInfoDto userInfoDto) {
        this.setId(userInfoDto.getId() == null ? UUID.randomUUID().toString() : userInfoDto.getId());
        this.setEmail(userInfoDto.getEmail());
        this.setDisplayName(userInfoDto.getDisplayName());
        this.setUsername(userInfoDto.getUsername());
    }
}
