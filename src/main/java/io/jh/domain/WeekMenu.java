package io.jh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import io.jh.domain.enumeration.Week;

/**
 * 一周菜单，用于在公众号上展示的菜品列表\n@author Jo
 */
@Entity
@Table(name = "week_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WeekMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "week", nullable = false)
    private Week week;

    /**
     * 菜品在所在天的顺序
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    @Column(name = "sort", nullable = false)
    private Integer sort;

    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "last_modified_by", length = 20, nullable = false)
    private String lastModifiedBy;

    @OneToMany(mappedBy = "weekMenu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WeekStock> weekStocks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("weekMenus")
    private Food food;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Week getWeek() {
        return week;
    }

    public WeekMenu week(Week week) {
        this.week = week;
        return this;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Integer getSort() {
        return sort;
    }

    public WeekMenu sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public WeekMenu lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public WeekMenu lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Set<WeekStock> getWeekStocks() {
        return weekStocks;
    }

    public WeekMenu weekStocks(Set<WeekStock> weekStocks) {
        this.weekStocks = weekStocks;
        return this;
    }

    public WeekMenu addWeekStocks(WeekStock weekStock) {
        this.weekStocks.add(weekStock);
        weekStock.setWeekMenu(this);
        return this;
    }

    public WeekMenu removeWeekStocks(WeekStock weekStock) {
        this.weekStocks.remove(weekStock);
        weekStock.setWeekMenu(null);
        return this;
    }

    public void setWeekStocks(Set<WeekStock> weekStocks) {
        this.weekStocks = weekStocks;
    }

    public Food getFood() {
        return food;
    }

    public WeekMenu food(Food food) {
        this.food = food;
        return this;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeekMenu)) {
            return false;
        }
        return id != null && id.equals(((WeekMenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WeekMenu{" +
            "id=" + getId() +
            ", week='" + getWeek() + "'" +
            ", sort=" + getSort() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
