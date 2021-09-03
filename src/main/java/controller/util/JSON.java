package controller.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JSON implements Type {

    @Override
    public <T> String getType(T object) {
        String listEmp = null;
        try {
            Gson gson = new Gson();
            listEmp = gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEmp;
    }

//    @Override
//    public <T> Object addEl(Object tClass,T object, HttpServletRequest request) throws IOException {
//        Gson gson = new Gson();
//        tClass = gson.fromJson(request.getReader(), object.getClass());
//        return tClass;
//    }
}
