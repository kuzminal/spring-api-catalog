package ru.kuzmin.apicatalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.domain.entity.Currency;

@Mapper(componentModel = "spring")
public interface ApiMapper {
    ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);
    CurrencyDTO entityToDTO(Currency currency);
    Currency DTOToEntity(CurrencyDTO currency);
}
