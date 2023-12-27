package com.winterhold.dao;

import com.winterhold.dto.category.CategoryRowDTO;
import com.winterhold.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    @Query("""
            SELECT new com.winterhold.dto.category.CategoryRowDTO(cat.name, cat.floor, cat.isle, cat.bay)
            FROM Category AS cat
            WHERE cat.name LIKE %:name%
            """)//diambil dari entity, : menandakan diambil dari parameter dibawah
//    pakai konstraktor categoryRowDTO, nama package harus ditulis lengkap kalau didalam string
    public Page<CategoryRowDTO> getRow(@Param("name") String name, Pageable pageable);
}
