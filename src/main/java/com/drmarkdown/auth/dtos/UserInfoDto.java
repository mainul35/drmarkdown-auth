package com.drmarkdown.auth.dtos;

import com.drmarkdown.auth.models.MarkdownUserModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDto extends BaseDto<MarkdownUserModel> {
    private String id;
    private String username;
    private String displayName;
    private List<String> roles;
    private String email;
    private String password;
    private String jwtToken;
    private Date createdAt;
    private Date modifiedAt;

    public UserInfoDto() {
    }

    public void mapEntityToDto(MarkdownUserModel markdownUserModel) {
        this.setId(markdownUserModel.getId());
        this.setUsername(markdownUserModel.getUsername());
        this.setDisplayName(markdownUserModel.getDisplayName());
        this.setRoles(markdownUserModel.getRoles());
        this.setEmail(markdownUserModel.getEmail());
        this.setPassword("******");
        this.setJwtToken(markdownUserModel.getJwtToken());
        this.setCreatedAt(markdownUserModel.getCreatedAt());
        this.setModifiedAt(markdownUserModel.getUpdatedAt());
    }
}
