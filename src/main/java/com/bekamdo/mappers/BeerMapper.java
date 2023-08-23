package com.bekamdo.mappers;

import com.bekamdo.domain.Beer;
import com.bekamdo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDTO beerToBeerDTO(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
