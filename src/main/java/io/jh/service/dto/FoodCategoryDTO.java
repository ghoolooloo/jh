package io.jh.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.jh.domain.FoodCategory} entity.
 */
@ApiModel(description = "菜品分类\n@author Jo")
public class FoodCategoryDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 10)
    private String name;

    /**
     * 编号
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "编号", required = true)
    private String sn;

    /**
     * 图标
     */
    @Size(max = 30)
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 序号
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    @ApiModelProperty(value = "序号", required = true)
    private Integer sort;

    /**
     * 创建者登录账号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "创建者登录账号", required = true)
    private String createdBy;

    @NotNull
    private Instant createdDate;

    private Instant lastModifiedDate;

    /**
     * 最后修改者登录账号
     */
    @Size(max = 20)
    @ApiModelProperty(value = "最后修改者登录账号")
    private String lastModifiedBy;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FoodCategoryDTO foodCategoryDTO = (FoodCategoryDTO) o;
        if (foodCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foodCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FoodCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sn='" + getSn() + "'" +
            ", icon='" + getIcon() + "'" +
            ", sort=" + getSort() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
