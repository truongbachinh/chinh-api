package controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;

public class JSON implements Type{

    @Override
    public <T> String getType(T object) throws UnsupportedEncodingException, JAXBException {
        Gson gson = new Gson();
        String listEmp = gson.toJson(object);
        return listEmp;
    }

//    @Override
//    public <T> String getType(Class<T> tClass) throws UnsupportedEncodingException, JAXBException {
//        Gson gson = new Gson();
//        String listEmp = gson.toJson(tClass);
//        return listEmp;
//    }
}
