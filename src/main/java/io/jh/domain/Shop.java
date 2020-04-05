package io.jh.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import io.jh.domain.enumeration.OffsetType;

/**
 * 门店\n@author Jo
 */
@Entity
@Table(name = "shop")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 门店名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    /**
     * 门店编号，即微信门店的poi_id
     */
    @NotNull
    @Size(max = 30)
    @Column(name = "sn", length = 30, nullable = false, unique = true)
    private String sn;

    /**
     * 联系电话
     */
    @Size(max = 20)
    @Column(name = "tel", length = 20)
    private String tel;

    /**
     * 详细地址（不含省市信息）
     */
    @Size(max = 50)
    @Column(name = "address", length = 50)
    private String address;

    /**
     * 省、直辖市、自治区
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "province", length = 20, nullable = false)
    private String province;

    /**
     * 城市
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "city", length = 20, nullable = false)
    private String city;

    /**
     * 地区
     */
    @Size(max = 20)
    @Column(name = "district", length = 20)
    private String district;

    /**
     * 坐标类型。由于门店来自微信，因此这里坐标类型固定为 MARS
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offset_type", nullable = false)
    private OffsetType offsetType;

    /**
     * 经度
     */
    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    /**
     * 开始营业时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @Column(name = "shop_open", length = 8)
    private String shopOpen;

    /**
     * 结束营业时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @Column(name = "shop_close", length = 8)
    private String shopClose;

    /**
     * 最大配送距离（相对于最近门店），单位：公里
     */
    @Min(value = 0)
    @Max(value = 50)
    @Column(name = "max_delivery_distance")
    private Integer maxDeliveryDistance;

    /**
     * 起送金额（单位：分）。是限制订单的priceTotal字段的最小值，低于这个值的订单不外送。自助订单不受此限制！
     */
    @Min(value = 0)
    @Max(value = 999999)
    @Column(name = "min_delivery_amount")
    private Integer minDeliveryAmount;

    /**
     * 正餐午餐供餐起始时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @Column(name = "lunch_serve_starting_at", length = 8)
    private String lunchServeStartingAt;

    /**
     * 正餐午餐供餐结束时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @Column(name = "lunch_serve_end_at", length = 8)
    private String lunchServeEndAt;

    /**
     * 正餐晚餐配供餐始时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @Column(name = "supper_serve_starting_at", length = 8)
    private String supperServeStartingAt;

    /**
     * 正餐晚餐供餐结束时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @Column(name = "supper_serve_end_at", length = 8)
    private String supperServeEndAt;

    @NotNull
    @Size(max = 20)
    @Column(name = "created_by", length = 20, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Size(max = 20)
    @Column(name = "last_modified_by", length = 20)
    private String lastModifiedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Shop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public Shop sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTel() {
        return tel;
    }

    public Shop tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public Shop address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public Shop province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public Shop city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public Shop district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public Shop offsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
        return this;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Shop longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Shop latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getShopOpen() {
        return shopOpen;
    }

    public Shop shopOpen(String shopOpen) {
        this.shopOpen = shopOpen;
        return this;
    }

    public void setShopOpen(String shopOpen) {
        this.shopOpen = shopOpen;
    }

    public String getShopClose() {
        return shopClose;
    }

    public Shop shopClose(String shopClose) {
        this.shopClose = shopClose;
        return this;
    }

    public void setShopClose(String shopClose) {
        this.shopClose = shopClose;
    }

    public Integer getMaxDeliveryDistance() {
        return maxDeliveryDistance;
    }

    public Shop maxDeliveryDistance(Integer maxDeliveryDistance) {
        this.maxDeliveryDistance = maxDeliveryDistance;
        return this;
    }

    public void setMaxDeliveryDistance(Integer maxDeliveryDistance) {
        this.maxDeliveryDistance = maxDeliveryDistance;
    }

    public Integer getMinDeliveryAmount() {
        return minDeliveryAmount;
    }

    public Shop minDeliveryAmount(Integer minDeliveryAmount) {
        this.minDeliveryAmount = minDeliveryAmount;
        return this;
    }

    public void setMinDeliveryAmount(Integer minDeliveryAmount) {
        this.minDeliveryAmount = minDeliveryAmount;
    }

    public String getLunchServeStartingAt() {
        return lunchServeStartingAt;
    }

    public Shop lunchServeStartingAt(String lunchServeStartingAt) {
        this.lunchServeStartingAt = lunchServeStartingAt;
        return this;
    }

    public void setLunchServeStartingAt(String lunchServeStartingAt) {
        this.lunchServeStartingAt = lunchServeStartingAt;
    }

    public String getLunchServeEndAt() {
        return lunchServeEndAt;
    }

    public Shop lunchServeEndAt(String lunchServeEndAt) {
        this.lunchServeEndAt = lunchServeEndAt;
        return this;
    }

    public void setLunchServeEndAt(String lunchServeEndAt) {
        this.lunchServeEndAt = lunchServeEndAt;
    }

    public String getSupperServeStartingAt() {
        return supperServeStartingAt;
    }

    public Shop supperServeStartingAt(String supperServeStartingAt) {
        this.supperServeStartingAt = supperServeStartingAt;
        return this;
    }

    public void setSupperServeStartingAt(String supperServeStartingAt) {
        this.supperServeStartingAt = supperServeStartingAt;
    }

    public String getSupperServeEndAt() {
        return supperServeEndAt;
    }

    public Shop supperServeEndAt(String supperServeEndAt) {
        this.supperServeEndAt = supperServeEndAt;
        return this;
    }

    public void setSupperServeEndAt(String supperServeEndAt) {
        this.supperServeEndAt = supperServeEndAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Shop createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Shop createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Shop lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Shop lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shop)) {
            return false;
        }
        return id != null && id.equals(((Shop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Shop{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sn='" + getSn() + "'" +
            ", tel='" + getTel() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", district='" + getDistrict() + "'" +
            ", offsetType='" + getOffsetType() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", shopOpen='" + getShopOpen() + "'" +
            ", shopClose='" + getShopClose() + "'" +
            ", maxDeliveryDistance=" + getMaxDeliveryDistance() +
            ", minDeliveryAmount=" + getMinDeliveryAmount() +
            ", lunchServeStartingAt='" + getLunchServeStartingAt() + "'" +
            ", lunchServeEndAt='" + getLunchServeEndAt() + "'" +
            ", supperServeStartingAt='" + getSupperServeStartingAt() + "'" +
            ", supperServeEndAt='" + getSupperServeEndAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
