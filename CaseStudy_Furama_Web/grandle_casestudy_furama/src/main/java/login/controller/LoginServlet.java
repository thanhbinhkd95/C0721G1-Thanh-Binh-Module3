package login.controller;

import com.mysql.cj.Session;
import login.model.bean.Account;
import login.model.service.Impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    LoginServiceImpl loginService = new LoginServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "login" :
                loginPage(request,response);
                break;
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Cookie arrCookie[] =request.getCookies();
       for(Cookie c : arrCookie){
           if(c.getName().equals("user")){
               request.setAttribute("username",c.getValue());
           }
           if(c.getName().equals("password")){
               request.setAttribute("password",c.getValue());
           }
       }

        request.getRequestDispatcher("view/login.jsp").forward(request,response);
    }



    private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Account account = loginService.login(user,password);
        if(account == null){
            request.setAttribute("message","Wrong user or password");
            request.getRequestDispatcher("view/login.jsp").forward(request,response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("account",account);
            Cookie cookieUser = new Cookie("user",user);
            Cookie cookiePassword = new Cookie("password",password);
            if(remember != null){
                cookiePassword.setMaxAge(60);
                cookieUser.setMaxAge(60);
            }else {
                cookiePassword.setMaxAge(0);
                cookieUser.setMaxAge(0);
            }
            response.addCookie(cookieUser);
            response.addCookie(cookiePassword);
            request.getRequestDispatcher("view/main_page.jsp").forward(request,response);
        }
    }

}
