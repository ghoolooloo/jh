package io.jh.repository;

import io.jh.domain.WeekMenu;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WeekMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeekMenuRepository extends JpaRepository<WeekMenu, Long> {
}
