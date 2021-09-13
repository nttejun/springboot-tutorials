package org.tutorials.servletdemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

public class HelloServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
    System.out.println("init");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
    ApplicationContext context = (ApplicationContext) getServletContext();
    HelloService helloService = context.getBean(HelloService.class);

    System.out.println("doGet");
    res.getWriter().println("<html>");
    res.getWriter().println("<head>");
    res.getWriter().println("<h1>servlet + " + helloService.getName() + "</h1>");
    res.getWriter().println("</head>");
    res.getWriter().println("</html>");
  }

  @Override
  public void destroy() {
    System.out.println("destroy");
  }
}
