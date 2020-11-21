package servlets;

import accounts.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public abstract class AccountServlet extends HttpServlet {

    /** Ссылка на {@link accounts.AccountService} */
    protected final AccountService accountService;

    public AccountServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * Метод установления нужного типа контента для response
     */
    protected void setContentType(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
    }
}
