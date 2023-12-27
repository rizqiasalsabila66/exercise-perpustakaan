package com.winterhold.dao;

import com.winterhold.dto.author.AuthorHeaderRowDTO;
import com.winterhold.dto.author.AuthorRowDTO;
import com.winterhold.dto.utility.DropdownDTO;
import com.winterhold.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("""
            SELECT new com.winterhold.dto.author.AuthorRowDTO(aut.id, CONCAT(aut.firstName,' ', aut.lastName),
            aut.deceasedDate, aut.education)
            FROM Author AS aut
            WHERE CONCAT(aut.firstName,' ', aut.lastName) LIKE %:name%
            """)
    public Page<AuthorRowDTO> getRow(@Param("name") String name, Pageable pageable);
    @Query("""
            SELECT new com.winterhold.dto.utility.DropdownDTO(aut.id, CONCAT(aut.firstName,' ', aut.lastName))
            FROM Author AS aut
            """)
    public List<DropdownDTO> getDropdown();

}
