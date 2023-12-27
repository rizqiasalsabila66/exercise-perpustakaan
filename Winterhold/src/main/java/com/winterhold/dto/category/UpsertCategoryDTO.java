package com.winterhold.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class UpsertCategoryDTO {


    @NotBlank(message = "Wajib diisi")
    @Size(max = 100, message = "Maksimal 100 char")
    private String name;

    @NotNull(message = "Wajib diisi")
    private  Integer floor;

    @NotBlank(message = "Wajib diisi")
    @Size(max = 10, message = "Maksimal 10 char")
    private  String isle;

    @NotBlank(message = "Wajib diisi")
    @Size(max = 10, message = "Maksimal 10 char")
    private  String bay;
}
