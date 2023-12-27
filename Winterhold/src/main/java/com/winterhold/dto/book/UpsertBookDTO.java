package com.winterhold.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class UpsertBookDTO {
    @NotBlank(message = "Wajib diisi")
    @Size(max = 20, message = "Maksimal 20 char")
    private String code;

    @NotBlank(message = "Wajib diisi")
    @Size(max = 100, message = "Maksimal 100 char")
    private  String title;

    private String categoryName;

    @NotNull(message = "Wajib diisi")
    private Long authorId;

    private Boolean isBorrowed;

    @Size(max = 500, message = "Maksimal 500 char")
    private String Summary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private Long totalPage;

}
