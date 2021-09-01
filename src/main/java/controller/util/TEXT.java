package controller.util;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

public class TEXT implements Type {

    @Override
    public <T> String getType(T object) {
        String result = null;
        try {
            List<T> intList = (List<T>) object;
            result = intList.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(" | "));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
