package se.jensen.linkan.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import se.jensen.linkan.springboot.dto.PostRequestDTO;
import se.jensen.linkan.springboot.dto.PostResponseDTO;
import se.jensen.linkan.springboot.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toEntity(PostRequestDTO dto);

    @Mapping(source = "user.id", target = "id")
    PostResponseDTO toResponse(Post post);

    void updateEntity(@MappingTarget Post post, PostRequestDTO dto);
}
