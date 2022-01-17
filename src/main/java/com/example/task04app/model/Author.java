package com.example.task04app.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

        private String lastName;
        private String firstName;
        private String patronymic;

}
