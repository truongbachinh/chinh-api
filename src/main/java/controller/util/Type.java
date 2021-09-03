package controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Type {
    <T> String getType(T object);
//   <T> Object addEl(Object tClass,T object, HttpServletRequest request) throws IOException;

}
