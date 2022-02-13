package com.example.task04app.dto;

import com.example.task04app.exception.marker.OnUpdate;
import com.example.task04app.service.util.BookDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = BookDeserializer.class)
public class BookDto {

    @NotNull(groups = OnUpdate.class, message = "Id should be not Null")
    private Long id;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
            message = "Incorrect ISBN number entered")
    private String isbn;

    @NotBlank(message = "Book name is mandatory")
    @Size(min = 2, max = 40, message = "Book name should be between 2 and 40 characters")
    private String name;

    @NotBlank(message = "Author is mandatory")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 40 characters")
    private String author;

    @NotNull(message = "The number of pages is mandatory")
    @Positive(message = "Page count should be greater than 0")
    private Integer page;

    @NotNull(message = "Weight is mandatory")
    @Positive(message = "Weight should be greater than 0")
    private Integer weightInGrams;

    @NotNull(message = "Price is mandatory")
    @PositiveOrZero(message = "Price cannot be negative")
    private Integer priceInKopecks;

}
