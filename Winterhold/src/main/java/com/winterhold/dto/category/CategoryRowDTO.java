package com.winterhold.dto.category;

import com.winterhold.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class CategoryRowDTO {
    private String name;
    private Integer floor;
    private String isle;
    private String bay;
//    private Long totalBook;
}
