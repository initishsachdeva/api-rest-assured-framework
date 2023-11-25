package services;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class BooleanConverter  implements Converter<String,Boolean> {
    @Override
    public Boolean convert(String s) {
        if (s.equalsIgnoreCase("YES")){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

}
