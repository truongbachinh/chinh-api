package controller.template;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class APIData extends DataTemplate{

    @Override
    protected void showBody(HttpServletResponse response, String string) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(string);
        out.flush();
        out.close();
    }

}
