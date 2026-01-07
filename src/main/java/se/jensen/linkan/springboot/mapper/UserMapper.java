package se.jensen.linkan.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import se.jensen.linkan.springboot.dto.UserRequestDTO;
import se.jensen.linkan.springboot.dto.UserResponseDTO;
import se.jensen.linkan.springboot.model.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponse(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget User user, UserRequestDTO dto);
}
