package controller.util;

import com.google.gson.Gson;

import javax.xml.bind.JAXBException;
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
}
