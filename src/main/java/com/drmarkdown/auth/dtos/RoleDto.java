package com.drmarkdown.auth.dtos;

import com.drmarkdown.auth.models.MarkdownRoleModel;
import lombok.Data;

@Data
public class RoleDto extends BaseDto<MarkdownRoleModel> {
    private String id;
    private String role;
    private String createdAt;
    private String modifiedAt;

    @Override
    public void mapEntityToDto(MarkdownRoleModel markdownRoleModel) {
        this.setId(markdownRoleModel.getId());
        this.setRole(markdownRoleModel.getRole());
        this.setCreatedAt(markdownRoleModel.getCreatedAt().toString());
        this.setModifiedAt(markdownRoleModel.getUpdatedAt().toString());
    }
}
