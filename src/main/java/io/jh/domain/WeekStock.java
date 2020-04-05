package io.jh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * 门店周库存\n@author Jo
 */
@Entity
@Table(name = "week_stock")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WeekStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 门店编号
     */
    @NotNull
    @Size(max = 30)
    @Column(name = "shop_sn", length = 30, nullable = false)
    private String shopSN;

    /**
     * 门店名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "shop_name", length = 20, nullable = false)
    private String shopName;

    /**
     * 菜品数量。售出超过这个数量时，将显示“已售完”（不是下架！）
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "food_quantity", nullable = false)
    private Integer foodQuantity;

    @ManyToOne
    @JsonIgnoreProperties("weekStocks")
    private WeekMenu weekMenu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopSN() {
        return shopSN;
    }

    public WeekStock shopSN(String shopSN) {
        this.shopSN = shopSN;
        return this;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public WeekStock shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getFoodQuantity() {
        return foodQuantity;
    }

    public WeekStock foodQuantity(Integer foodQuantity) {
        this.foodQuantity = foodQuantity;
        return this;
    }

    public void setFoodQuantity(Integer foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public WeekMenu getWeekMenu() {
        return weekMenu;
    }

    public WeekStock weekMenu(WeekMenu weekMenu) {
        this.weekMenu = weekMenu;
        return this;
    }

    public void setWeekMenu(WeekMenu weekMenu) {
        this.weekMenu = weekMenu;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeekStock)) {
            return false;
        }
        return id != null && id.equals(((WeekStock) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WeekStock{" +
            "id=" + getId() +
            ", shopSN='" + getShopSN() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", foodQuantity=" + getFoodQuantity() +
            "}";
    }
}
