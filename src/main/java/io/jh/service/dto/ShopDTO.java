package io.jh.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
            "}";
    }
}
