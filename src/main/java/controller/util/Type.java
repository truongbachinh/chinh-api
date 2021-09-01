package controller.util;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;

public interface Type {
    <T> String getType(T object);

}
