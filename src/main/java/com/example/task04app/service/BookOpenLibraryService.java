package com.example.task04app.service;

import com.example.task04app.external.dto.BookDbAndOpenLibraryDtoList;

public interface BookOpenLibraryService {

    BookDbAndOpenLibraryDtoList getBookDtosByAuthor(String author);

}
