package com.winterhold.dao;

import com.winterhold.dto.book.BookRowByAuthorDTO;
import com.winterhold.dto.book.BookRowByCategoryDTO;
import com.winterhold.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    @Query("""
            Select new com.winterhold.dto.book.BookRowByCategoryDTO(book.code, book.title,book.categoryName, CONCAT(aut.firstName,' ', aut.lastName),
            book.isBorrowed,book.summary, book.releaseDate, book.totalPage)
            FROM Book AS book
            JOIN book.author AS aut
            """)
    public Page<BookRowByCategoryDTO> getRow(Pageable pageable);

    @Query("""
            Select new com.winterhold.dto.book.BookRowByCategoryDTO(book.code, book.title, CONCAT(aut.firstName,' ', aut.lastName),
            book.isBorrowed,book.summary, book.releaseDate, book.totalPage)
            FROM Book AS book
            JOIN book.author AS aut
            WHERE book.categoryName = :categoryName
            AND (:title IS NULL OR book.title LIKE %:title%)
            AND (:author IS NULL OR CONCAT(aut.firstName,' ', aut.lastName) LIKE %:author%)
            AND (:isBorrowed IS NULL OR book.isBorrowed = isBorrowed)
            """)
    public Page<BookRowByCategoryDTO> getRowByCategory(@Param("categoryName") String categoryName, @Param("title") String title,
                                                       @Param("author") String author, @Param("isBorrowed") Boolean isBorrowed,
                                                       Pageable pageable);

    @Query("""
            Select new com.winterhold.dto.book.BookRowByCategoryDTO(book.code, book.title, CONCAT(aut.firstName,' ', aut.lastName),
            book.isBorrowed,book.summary, book.releaseDate, book.totalPage)
            FROM Book AS book
            JOIN book.author AS aut
            WHERE book.categoryName = :categoryName
            """)
    public Page<BookRowByCategoryDTO> getRowByCategory(@Param("categoryName") String categoryName,Pageable pageable);
    @Query("""
            Select new com.winterhold.dto.book.BookRowByAuthorDTO(book.code, book.title, cat.name,
            book.isBorrowed, book.releaseDate, book.totalPage)
            FROM Book AS book
            JOIN book.category AS cat
            WHERE book.authorId = :authorId
            """)
    public Page<BookRowByAuthorDTO> getRowByAuthor(@Param("authorId") Long authorId, Pageable pageable);
    @Query("""
            SELECT COUNT(book.code)
            FROM Book AS book
            WHERE book.categoryName = :categoryName
            """)
    public Long countByCategory(@Param("categoryName") String categoryName);

    @Query("""
            SELECT COUNT(book.code)
            FROM Book AS book
            WHERE book.authorId = :authorId
            """)
    public Long countByAuthor(@Param("authorId") Long authorId);

}
