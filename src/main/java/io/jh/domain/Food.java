package io.jh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

import io.jh.domain.enumeration.FeedingMode;

/**
 * 菜品\n@author Jo
 */
@Entity
@Table(name = "food")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    /**
     * 编号
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "sn", length = 10, nullable = false, unique = true)
    private String sn;

    /**
     * 缩略图
     */
    @Size(max = 200)
    @Column(name = "thumbnail", length = 200)
    private String thumbnail;

    /**
     * 大图
     */
    @Size(max = 200)
    @Column(name = "picture", length = 200)
    private String picture;

    /**
     * 单价（单位：分）
     */
    @NotNull
    @Min(value = 1)
    @Max(value = 999999)
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * 包装费（单位：分）
     */
    @Min(value = 0)
    @Max(value = 999999)
    @Column(name = "packing_fee")
    private Integer packingFee;

    /**
     * 描述
     */
    @Size(max = 300)
    @Column(name = "jhi_desc", length = 300)
    private String desc;

    /**
     * 序号
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    @Column(name = "sort", nullable = false)
    private Integer sort;

    /**
     * 供餐方式
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "feeding_mode", nullable = false)
    private FeedingMode feedingMode;

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

    @ManyToOne
    @JsonIgnoreProperties("foods")
    private FoodCategory category;

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

    public Food name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public Food sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Food thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPicture() {
        return picture;
    }

    public Food picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public Food price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPackingFee() {
        return packingFee;
    }

    public Food packingFee(Integer packingFee) {
        this.packingFee = packingFee;
        return this;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public String getDesc() {
        return desc;
    }

    public Food desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSort() {
        return sort;
    }

    public Food sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public FeedingMode getFeedingMode() {
        return feedingMode;
    }

    public Food feedingMode(FeedingMode feedingMode) {
        this.feedingMode = feedingMode;
        return this;
    }

    public void setFeedingMode(FeedingMode feedingMode) {
        this.feedingMode = feedingMode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Food createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Food createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Food lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Food lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public Food category(FoodCategory foodCategory) {
        this.category = foodCategory;
        return this;
    }

    public void setCategory(FoodCategory foodCategory) {
        this.category = foodCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        return id != null && id.equals(((Food) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Food{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sn='" + getSn() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", picture='" + getPicture() + "'" +
            ", price=" + getPrice() +
            ", packingFee=" + getPackingFee() +
            ", desc='" + getDesc() + "'" +
            ", sort=" + getSort() +
            ", feedingMode='" + getFeedingMode() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
