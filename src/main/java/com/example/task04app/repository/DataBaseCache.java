package com.example.task04app.repository;

import com.example.task04app.dto.AuthorDto;
import com.example.task04app.dto.BookDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DataBaseCache {
    private static Long idCount = 0L;
    private static HashMap<Long, BookDto> cacheBookRepository = new HashMap<>();

    public static Long getIdCount() {
        return idCount;
    }

    public static void setIdCount(Long idCount) {
        DataBaseCache.idCount = idCount;
    }

    public static BookDto getBook (Long id){
        return cacheBookRepository.get(id);
    }

    public static void setBook (Long id, BookDto bookDto) {
        cacheBookRepository.put(id, bookDto);
    }

    public static HashMap<Long, BookDto> getCacheBookRepository() {
        return cacheBookRepository;
    }

    public static void deleteBook (Long id){
        cacheBookRepository.remove(id);
    }

    static {
        cacheBookRepository.put(1L, new BookDto(1L, "978-5-17-046200-1", "Дядя Федор, кот и пес",
                new AuthorDto("Успенский", "Эдуард", "Николаевич"), 100,
                500, 12));

        cacheBookRepository.put(2L, new BookDto(2L, "978-5-17-120349-8", "Dad",
                new AuthorDto("Рыбаков", "Игорь", ""), 200,
                600, 25));

        cacheBookRepository.put(3L, new BookDto(3L, "5-04-002894-6", "Joke",
                new AuthorDto("Smith", "John", ""), 300,
                800, 45));

        idCount = 3L;
    }
}
