package io.jh.service.mapper;


import io.jh.domain.*;
import io.jh.service.dto.WeekStockDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WeekStock} and its DTO {@link WeekStockDTO}.
 */
@Mapper(componentModel = "spring", uses = {WeekMenuMapper.class})
public interface WeekStockMapper extends EntityMapper<WeekStockDTO, WeekStock> {

    @Mapping(source = "weekMenu.id", target = "weekMenuId")
    WeekStockDTO toDto(WeekStock weekStock);

    @Mapping(source = "weekMenuId", target = "weekMenu")
    WeekStock toEntity(WeekStockDTO weekStockDTO);

    default WeekStock fromId(Long id) {
        if (id == null) {
            return null;
        }
        WeekStock weekStock = new WeekStock();
        weekStock.setId(id);
        return weekStock;
    }
}
