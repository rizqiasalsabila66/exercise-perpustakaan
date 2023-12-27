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
public class AuthorHeaderRowDTO {

    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private LocalDate deceasedDate;
    private String Education;
    private String summary;

}
