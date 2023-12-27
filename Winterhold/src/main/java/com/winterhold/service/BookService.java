package com.winterhold.service;

import com.winterhold.dao.AuthorRepository;
import com.winterhold.dao.BookRepository;
import com.winterhold.dao.LoanRepository;
import com.winterhold.dto.book.BookRowByCategoryDTO;
import com.winterhold.dto.book.ResponseBookDTO;
import com.winterhold.dto.book.UpsertBookDTO;
import com.winterhold.dto.utility.DropdownDTO;
import com.winterhold.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LoanRepository loanRepository;


    public Page<BookRowByCategoryDTO> getBookRows(int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("code"));
        var rows = bookRepository.getRow(pageable);
        return rows;
    }

    public ResponseBookDTO save(UpsertBookDTO dto) {
        var entity = new Book();
        entity.setCode(dto.getCode());
        entity.setTitle(dto.getTitle());
        entity.setCategoryName(dto.getCategoryName());
        entity.setAuthorId(dto.getAuthorId());
        entity.setIsBorrowed(dto.getIsBorrowed());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setSummary(dto.getSummary());
        entity.setTotalPage(dto.getTotalPage());
        var response = bookRepository.save(entity);
        var responseDTO = new ResponseBookDTO(
                response.getCode(),
                response.getTitle(),
                response.getCategoryName(),
                response.getAuthorId(),
                response.getIsBorrowed(),
                response.getSummary(),
                response.getReleaseDate(),
                response.getTotalPage()
        );
        return responseDTO;
    }

    public UpsertBookDTO findOne(String code){
        var entity = bookRepository.findById(code).get();
        var dto = new UpsertBookDTO(
                entity.getCode(), entity.getTitle(), entity.getCategoryName(), entity.getAuthorId(),
                entity.getIsBorrowed(),entity.getSummary(),entity.getReleaseDate(),entity.getTotalPage()
        );
        return dto;

    }

    public void delete(String code){
        bookRepository.deleteById(code);
    }

    public Long countLoanByBook (String code){
        return loanRepository.countByBook(code);
    }

    public List<DropdownDTO> getAuthorDropdown(){
        return authorRepository.getDropdown();
    }

    public String getSummary(String code){
        var summary = bookRepository.findById(code).get().getSummary();
        return summary;
    }

}
