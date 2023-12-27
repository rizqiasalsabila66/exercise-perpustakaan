package com.basilisk.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong

public class RespondUpsertCategoryDTO {
    private String name;
    private  Integer floor;
    private  String isle;
    private  String bay;
}