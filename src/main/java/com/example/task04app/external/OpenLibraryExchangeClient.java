package com.example.task04app.external;

import com.example.task04app.external.dto.BookOpenLibraryDto;

import java.util.List;

public interface OpenLibraryExchangeClient {

    List<BookOpenLibraryDto> getBookDtosByAuthor(String author);

}
