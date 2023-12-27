package com.winterhold.service;

import com.winterhold.dao.AuthorRepository;
import com.winterhold.dao.BookRepository;
import com.winterhold.dto.author.AuthorHeaderRowDTO;
import com.winterhold.dto.author.AuthorResponseDTO;
import com.winterhold.dto.author.AuthorRowDTO;
import com.winterhold.dto.author.UpsertAuthorDTO;
import com.winterhold.dto.book.BookRowByAuthorDTO;
import com.winterhold.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Page<AuthorRowDTO> getAuthorRows(String name, int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("id"));
        var rows = authorRepository.getRow(name,pageable);
        return rows;
    }

    public AuthorResponseDTO save(UpsertAuthorDTO dto) {
        var entity = new Author();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setDeceasedDate(dto.getDeceasedDate());
        entity.setEducation(dto.getEducation());
        entity.setSummary(dto.getSummary());
        var response = authorRepository.save(entity);
        var responseDTO = new AuthorResponseDTO(
                response.getId(),
                response.getTitle(),
                response.getFirstName(),
                response.getLastName(),
                response.getBirthDate(),
                response.getDeceasedDate(),
                response.getEducation(),
                response.getSummary()
        );
        return responseDTO;
    }

    public  UpsertAuthorDTO findOne(Long id){
        var entity = authorRepository.findById(id).get();
        var dto = new UpsertAuthorDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getDeceasedDate(),
                entity.getEducation(),
                entity.getSummary()
        );
        return dto;
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }

    public Long countByAuthorId (Long id){
        return bookRepository.countByAuthor(id);
    }

    public Page<BookRowByAuthorDTO> getBookRowsByAuthor(Long id, int page) {
        var pageable = PageRequest.of(page-1,10, Sort.by("code"));
        var rows = bookRepository.getRowByAuthor(id,pageable);
        return rows;
    }

    public AuthorHeaderRowDTO getHeaderInfo(Long id){
        var author = authorRepository.findById(id).get();
        String fullName = author.getFirstName()+" "+author.getLastName();
        var authorHeader = new AuthorHeaderRowDTO();
        authorHeader.setId(author.getId());
        authorHeader.setFullName(fullName);
        authorHeader.setBirthDate(author.getBirthDate());
        authorHeader.setDeceasedDate(author.getDeceasedDate());
        authorHeader.setEducation(author.getEducation());
        authorHeader.setSummary(author.getSummary());
        return authorHeader;
    }

}
