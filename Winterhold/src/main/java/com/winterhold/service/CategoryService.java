package com.winterhold.service;

import com.winterhold.dao.BookRepository;
import com.winterhold.dao.CategoryRepository;
import com.winterhold.dto.book.BookRowByCategoryDTO;
import com.winterhold.dto.category.CategoryRowDTO;
import com.winterhold.dto.category.UpsertCategoryDTO;
import com.basilisk.dto.category.RespondUpsertCategoryDTO;
import com.winterhold.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public Page<CategoryRowDTO> getCategoryRows(String name, int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("name"));
        var rows = categoryRepository.getRow(name,pageable);
        return rows;
    }

    public Long totalBook(String name){
        var book = bookRepository.countByCategory(name);
        return book;
    }

    public RespondUpsertCategoryDTO save(UpsertCategoryDTO dto) {
        var entity = new Category();
        entity.setName(dto.getName());
        entity.setFloor(dto.getFloor());
        entity.setIsle(dto.getIsle());
        entity.setBay(dto.getBay());
        var response = categoryRepository.save(entity);
        var responseDTO = new RespondUpsertCategoryDTO(
                response.getName(),
                response.getFloor(),
                response.getIsle(),
                response.getBay()
        );
        return responseDTO;
    }

    public  UpsertCategoryDTO findOne(String name){
        var entity = categoryRepository.findById(name).get();
        var dto = new UpsertCategoryDTO(
                entity.getName(), entity.getFloor(), entity.getIsle(), entity.getBay()
        );
        return dto;

    }

    public void delete(String name){
        categoryRepository.deleteById(name);
    }

    public Long countByCategoryName (String name){
        return bookRepository.countByCategory(name);

    }

    public Page<BookRowByCategoryDTO> getBooks(String name, String title, String author, Boolean isBorrowed, Integer page) {
        var pageable = PageRequest.of(page - 1, 10, Sort.by("code"));
        var rows = bookRepository.getRowByCategory(name,title,author,isBorrowed,pageable);
        return rows;
    }
    public Page<BookRowByCategoryDTO> getBooks(String name, Integer page) {
        var pageable = PageRequest.of(page - 1, 10, Sort.by("code"));
        var rows = bookRepository.getRowByCategory(name,pageable);
        return rows;
    }

    public String getHeaderInfo(String name){
        var entity = categoryRepository.findById(name).get();
        var categoryName = entity.getName();
        return categoryName;
    }

}
