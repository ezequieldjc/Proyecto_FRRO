package Controladores;

import org.apache.commons.validator.GenericValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VerificarDate {

    public boolean isValidDate(String a){
        return GenericValidator.isDate(a, "yyyy/MM/dd",true);
    }
}
