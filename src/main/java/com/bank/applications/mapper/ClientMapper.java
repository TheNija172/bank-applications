package com.bank.applications.mapper;

import com.bank.applications.dto.ClientResponse;
import com.bank.applications.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse toResponse(Client client);

    Client toEntity(ClientResponse response);
}
