package io.jh.service.mapper;


import io.jh.domain.*;
import io.jh.service.dto.WeekMenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WeekMenu} and its DTO {@link WeekMenuDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodMapper.class})
public interface WeekMenuMapper extends EntityMapper<WeekMenuDTO, WeekMenu> {

    @Mapping(source = "food.id", target = "foodId")
    WeekMenuDTO toDto(WeekMenu weekMenu);

    @Mapping(target = "weekStocks", ignore = true)
    @Mapping(target = "removeWeekStocks", ignore = true)
    @Mapping(source = "foodId", target = "food")
    WeekMenu toEntity(WeekMenuDTO weekMenuDTO);

    default WeekMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        WeekMenu weekMenu = new WeekMenu();
        weekMenu.setId(id);
        return weekMenu;
    }
}
