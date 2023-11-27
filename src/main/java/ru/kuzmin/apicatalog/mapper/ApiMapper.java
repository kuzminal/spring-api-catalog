package ru.kuzmin.apicatalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.kuzmin.apicatalog.domain.dto.CityDTO;
import ru.kuzmin.apicatalog.domain.dto.CountryDTO;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.domain.dto.StateDTO;
import ru.kuzmin.apicatalog.domain.entity.City;
import ru.kuzmin.apicatalog.domain.entity.Country;
import ru.kuzmin.apicatalog.domain.entity.Currency;
import ru.kuzmin.apicatalog.domain.entity.State;

@Mapper(componentModel = "spring")
public interface ApiMapper {
    ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);
    CurrencyDTO entityToDTO(Currency currency);
    Currency DTOToEntity(CurrencyDTO currency);
    @Mapping(target="country", ignore = true)
    StateDTO entityToDTO(State state);
    State DTOtoEntity(StateDTO state);
    CountryDTO entityToDTO(CountryDTO country);
    Country DTOtoEntity(CountryDTO country);
    CityDTO entityToDTO(City city);
    City DTOtoEntity(CityDTO city);
}
