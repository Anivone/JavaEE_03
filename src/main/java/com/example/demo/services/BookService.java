package com.example.demo.services;

import com.example.demo.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service()
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;

    public Book createBook(Book book) {
        return entityManager.merge(book);
    }

    public List<Book> getBooks() {
        List<Book> result = entityManager.createQuery("SELECT b FROM Book b").getResultList();
        return result;
    }

    public List<Book> filterBooks(String filter) {
        Query query = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.title LIKE :filter OR b.isbn LIKE :filter or b.author LIKE :filter"
        );
        query.setParameter("filter", filter);

        return query.getResultList();
    }

    public Book getBookById(int id) {
        return entityManager.find(Book.class, id);
    }

}
