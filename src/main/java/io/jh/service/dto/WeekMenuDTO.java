package io.jh.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.jh.domain.enumeration.Week;

/**
 * A DTO for the {@link io.jh.domain.WeekMenu} entity.
 */
@ApiModel(description = "一周菜单，用于在公众号上展示的菜品列表\n@author Jo")
public class WeekMenuDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Week week;

    /**
     * 菜品在所在天的顺序
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    @ApiModelProperty(value = "菜品在所在天的顺序", required = true)
    private Integer sort;

    @NotNull
    private Instant lastModifiedDate;

    @NotNull
    @Size(max = 20)
    private String lastModifiedBy;


    private Long foodId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WeekMenuDTO weekMenuDTO = (WeekMenuDTO) o;
        if (weekMenuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weekMenuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WeekMenuDTO{" +
            "id=" + getId() +
            ", week='" + getWeek() + "'" +
            ", sort=" + getSort() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", foodId=" + getFoodId() +
            "}";
    }
}
