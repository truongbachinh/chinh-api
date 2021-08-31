package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TEXT implements Type{

    @Override
    public <T> String getType(T object) throws UnsupportedEncodingException, JAXBException {

        List<T> intList = (List<T>) object;
        String result = intList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(" | "));
        return  result;
    }
}
