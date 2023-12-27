package com.winterhold.dto.author;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class AuthorRowDTO {

    private Long id;
    private String fullName;
    private LocalDate deceasedDate;
    private String Education;

}
