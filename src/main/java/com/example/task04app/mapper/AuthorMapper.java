package com.example.task04app.mapper;

import com.example.task04app.dto.AuthorDto;
import com.example.task04app.model.Author;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Named(value = "authorDto")
    AuthorDto toAuthorDto (Author author);

    @Named(value = "author")
    Author toAuthor (AuthorDto authorDto);

    @IterableMapping(qualifiedByName = "author")
    List<Author> mapToAuthorList (List<AuthorDto> authorDto);

    @IterableMapping(qualifiedByName = "authorDto")
    List<AuthorDto> mapToAuthorDtoList (List<Author> author);
}
