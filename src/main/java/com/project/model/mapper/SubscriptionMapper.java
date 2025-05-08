package com.project.model.mapper;

import com.project.dto.SubscriptionAddRequest;
import com.project.dto.SubscriptionResponse;
import com.project.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toEntity(SubscriptionAddRequest dto);

    @Mapping(source = "user.id", target = "userId")
    SubscriptionResponse toDto(Subscription entity);
}
