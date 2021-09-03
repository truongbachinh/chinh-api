package controller.util;


import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class XML implements Type {
    @Override
    public <T> String getType(T object) {
        String xmlContent = null;
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = null;

            jaxbContext = JAXBContext.newInstance(object.getClass());

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(object, sw);

            //Verify XML Content
            xmlContent = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlContent;

    }

//    @Override
//    public <T> Object addEl(Object tClass, T object, HttpServletRequest request) throws IOException {
//        return null;
//    }
}
