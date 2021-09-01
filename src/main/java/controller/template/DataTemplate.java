package controller.template;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class DataTemplate {


    protected abstract void showBody(HttpServletResponse response, String string) throws IOException;
    public final void showData(HttpServletResponse response, String string) throws IOException {
        showBody(response,string);
    }

}
