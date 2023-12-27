package com.winterhold.dto.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DropdownDTO {
    private Object value;
//    object agar semua tipe data id bisa masuk
    private String textContent;

}
