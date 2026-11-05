package com.bank.applications.mapper;

import com.bank.applications.dto.ApplicationCreateRequest;
import com.bank.applications.dto.ApplicationResponse;
import com.bank.applications.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface ApplicationMapper {

    ApplicationResponse toResponse(Application application);

    @Mapping(target = "client", ignore = true)
    Application toEntity(ApplicationCreateRequest request);
}
