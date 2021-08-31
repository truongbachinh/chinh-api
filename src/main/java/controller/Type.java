package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;

public interface Type {
    <T> String getType(T object) throws UnsupportedEncodingException, JAXBException;

}
