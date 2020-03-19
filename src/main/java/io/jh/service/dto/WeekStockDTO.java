package io.jh.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.jh.domain.WeekStock} entity.
 */
@ApiModel(description = "门店周库存\n@author Jo")
public class WeekStockDTO implements Serializable {
    
    private Long id;

    /**
     * 门店编号
     */
    @NotNull
    @Size(max = 30)
    @ApiModelProperty(value = "门店编号", required = true)
    private String shopSN;

    /**
     * 门店名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "门店名称", required = true)
    private String shopName;

    /**
     * 菜品数量。售出超过这个数量时，将显示“已售完”（不是下架！）
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "菜品数量。售出超过这个数量时，将显示“已售完”（不是下架！）", required = true)
    private Integer foodQuantity;


    private Long weekMenuId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopSN() {
        return shopSN;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(Integer foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public Long getWeekMenuId() {
        return weekMenuId;
    }

    public void setWeekMenuId(Long weekMenuId) {
        this.weekMenuId = weekMenuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WeekStockDTO weekStockDTO = (WeekStockDTO) o;
        if (weekStockDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weekStockDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WeekStockDTO{" +
            "id=" + getId() +
            ", shopSN='" + getShopSN() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", foodQuantity=" + getFoodQuantity() +
            ", weekMenuId=" + getWeekMenuId() +
            "}";
    }
}
