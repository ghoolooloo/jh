package io.jh.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.jh.domain.enumeration.OffsetType;

/**
 * A DTO for the {@link io.jh.domain.Shop} entity.
 */
@ApiModel(description = "门店\n@author Jo")
public class ShopDTO implements Serializable {
    
    private Long id;

    /**
     * 门店名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "门店名称", required = true)
    private String name;

    /**
     * 门店编号，即微信门店的poi_id
     */
    @NotNull
    @Size(max = 30)
    @ApiModelProperty(value = "门店编号，即微信门店的poi_id", required = true)
    private String sn;

    /**
     * 联系电话
     */
    @Size(max = 20)
    @ApiModelProperty(value = "联系电话")
    private String tel;

    /**
     * 详细地址（不含省市信息）
     */
    @Size(max = 50)
    @ApiModelProperty(value = "详细地址（不含省市信息）")
    private String address;

    /**
     * 省、直辖市、自治区
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "省、直辖市、自治区", required = true)
    private String province;

    /**
     * 城市
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "城市", required = true)
    private String city;

    /**
     * 地区
     */
    @Size(max = 20)
    @ApiModelProperty(value = "地区")
    private String district;

    /**
     * 坐标类型。由于门店来自微信，因此这里坐标类型固定为 MARS
     */
    @NotNull
    @ApiModelProperty(value = "坐标类型。由于门店来自微信，因此这里坐标类型固定为 MARS", required = true)
    private OffsetType offsetType;

    /**
     * 经度
     */
    @NotNull
    @ApiModelProperty(value = "经度", required = true)
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull
    @ApiModelProperty(value = "纬度", required = true)
    private Double latitude;

    /**
     * 开始营业时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @ApiModelProperty(value = "开始营业时间。格式：05:00、19:00")
    private String shopOpen;

    /**
     * 结束营业时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @ApiModelProperty(value = "结束营业时间。格式：05:00、19:00")
    private String shopClose;

    /**
     * 最大配送距离（相对于最近门店），单位：公里
     */
    @Min(value = 0)
    @Max(value = 50)
    @ApiModelProperty(value = "最大配送距离（相对于最近门店），单位：公里")
    private Integer maxDeliveryDistance;

    /**
     * 起送金额（单位：分）。是限制订单的priceTotal字段的最小值，低于这个值的订单不外送。自助订单不受此限制！
     */
    @Min(value = 0)
    @Max(value = 999999)
    @ApiModelProperty(value = "起送金额（单位：分）。是限制订单的priceTotal字段的最小值，低于这个值的订单不外送。自助订单不受此限制！")
    private Integer minDeliveryAmount;

    /**
     * 正餐午餐供餐起始时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @ApiModelProperty(value = "正餐午餐供餐起始时间。格式：05:00、19:00")
    private String lunchServeStartingAt;

    /**
     * 正餐午餐供餐结束时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @ApiModelProperty(value = "正餐午餐供餐结束时间。格式：05:00、19:00")
    private String lunchServeEndAt;

    /**
     * 正餐晚餐配供餐始时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @ApiModelProperty(value = "正餐晚餐配供餐始时间。格式：05:00、19:00")
    private String supperServeStartingAt;

    /**
     * 正餐晚餐供餐结束时间。格式：05:00、19:00
     */
    @Size(max = 8)
    @ApiModelProperty(value = "正餐晚餐供餐结束时间。格式：05:00、19:00")
    private String supperServeEndAt;

    @NotNull
    @Size(max = 20)
    private String createdBy;

    @NotNull
    private Instant createdDate;

    private Instant lastModifiedDate;

    @Size(max = 20)
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getShopOpen() {
        return shopOpen;
    }

    public void setShopOpen(String shopOpen) {
        this.shopOpen = shopOpen;
    }

    public String getShopClose() {
        return shopClose;
    }

    public void setShopClose(String shopClose) {
        this.shopClose = shopClose;
    }

    public Integer getMaxDeliveryDistance() {
        return maxDeliveryDistance;
    }

    public void setMaxDeliveryDistance(Integer maxDeliveryDistance) {
        this.maxDeliveryDistance = maxDeliveryDistance;
    }

    public Integer getMinDeliveryAmount() {
        return minDeliveryAmount;
    }

    public void setMinDeliveryAmount(Integer minDeliveryAmount) {
        this.minDeliveryAmount = minDeliveryAmount;
    }

    public String getLunchServeStartingAt() {
        return lunchServeStartingAt;
    }

    public void setLunchServeStartingAt(String lunchServeStartingAt) {
        this.lunchServeStartingAt = lunchServeStartingAt;
    }

    public String getLunchServeEndAt() {
        return lunchServeEndAt;
    }

    public void setLunchServeEndAt(String lunchServeEndAt) {
        this.lunchServeEndAt = lunchServeEndAt;
    }

    public String getSupperServeStartingAt() {
        return supperServeStartingAt;
    }

    public void setSupperServeStartingAt(String supperServeStartingAt) {
        this.supperServeStartingAt = supperServeStartingAt;
    }

    public String getSupperServeEndAt() {
        return supperServeEndAt;
    }

    public void setSupperServeEndAt(String supperServeEndAt) {
        this.supperServeEndAt = supperServeEndAt;
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

        ShopDTO shopDTO = (ShopDTO) o;
        if (shopDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shopDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
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
