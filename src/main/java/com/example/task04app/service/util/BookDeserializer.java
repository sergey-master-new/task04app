package com.example.task04app.service.util;

import com.example.task04app.dto.AuthorDto;
import com.example.task04app.dto.BookDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class BookDeserializer extends StdDeserializer<BookDto> {

    protected BookDeserializer(Class<?> vc) {
        super(vc);
    }

    public BookDeserializer() {
        this(null);
    }

    @Override
    public BookDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        Long id = root.get("id").asLong();
        String isbn = root.get("isbn").asText();
        String name = root.get("name").asText();

        JsonNode author = root.get("author");
        String authorName = author.get("firstName").asText();
        String authorLastName = author.get("lastName").asText();
        String authorPatronymic = author.get("patronymic").asText();

        Integer pageCount = root.get("pageCount").asInt();
        Integer weightInGrams = root.get("weightInGrams").asInt();
        Integer priceInKopecks = root.get("priceInKopecks").asInt();


        return new BookDto(id,
                isbn,
                name,
                new AuthorDto(authorName, authorLastName, authorPatronymic),
                pageCount,
                weightInGrams,
                priceInKopecks);
    }
}
