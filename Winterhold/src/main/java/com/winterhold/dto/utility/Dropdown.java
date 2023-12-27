package com.winterhold.dto.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Dropdown {
    public static List<DropdownDTO> getEmployeeLevelDropdown() {
        List<DropdownDTO> employeeLevel = new LinkedList<>();
        employeeLevel.add(new DropdownDTO("National_Sales_Director", "National Sales Director"));
        employeeLevel.add(new DropdownDTO("Regional_Sales_Director", "Regional Sales Director"));
        employeeLevel.add(new DropdownDTO("Sales_Manager", "Sales Manager"));
        employeeLevel.add(new DropdownDTO("Inside_Sales_Manager", "Inside Sales Manager"));
        employeeLevel.add(new DropdownDTO("Outside_Sales_Manager", "Outside Sales Manager"));
        employeeLevel.add(new DropdownDTO("Sales_Assistant", "Sales Assistant"));
        employeeLevel.add(new DropdownDTO("Sales_Engineer", "Sales Engineer"));
        employeeLevel.add(new DropdownDTO("Wholesale_Sales", "Wholesale Sales"));
        employeeLevel.add(new DropdownDTO("Retail_Sales", "Retail Sales"));
        return employeeLevel;
    }

    public static List<DropdownDTO> getRRoleDropdown(){
        var dropdownDTO = new ArrayList<DropdownDTO>();
        dropdownDTO.add(new DropdownDTO("Administrator", "Administrator"));
        dropdownDTO.add(new DropdownDTO("Salesman", "Salesman"));
        dropdownDTO.add(new DropdownDTO("Finance", "Finance"));
        return dropdownDTO;
    }
}
