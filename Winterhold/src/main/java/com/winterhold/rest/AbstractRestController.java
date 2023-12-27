package com.winterhold.rest;


import com.winterhold.dto.utility.ValidationDTO;
import com.winterhold.utility.MapperHelper;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRestController {

    //    methodnya default
    List<ValidationDTO> getErrors(List<ObjectError> errors){
        var dto = new ArrayList<ValidationDTO>();
        for(var error : errors){
            var fieldName = MapperHelper.getStringField(error.getArguments()[0], "defaultMessage");
//            semua object tidak punya setter getter
            fieldName = (fieldName.equals("")) ? "object" : fieldName;
            var messsage = error.getDefaultMessage();
            dto.add(new ValidationDTO(fieldName,messsage));
        }
        return dto;
    }

}
