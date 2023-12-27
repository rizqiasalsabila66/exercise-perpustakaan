package com.winterhold.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class ResponseBookDTO {
    private String code;
    private String title;
    private String categoryName;
    private Long authorId;
    private Boolean isBorrowed;
    private String summary;
    private LocalDate releaseDate;
    private Long totalPage;
}
