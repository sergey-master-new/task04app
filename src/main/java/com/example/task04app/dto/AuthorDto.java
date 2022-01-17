package com.example.task04app.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthorDto {

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastName;

    private String firstName;

    private String patronymic;

    public AuthorDto() {
    }

    public AuthorDto(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }
}
