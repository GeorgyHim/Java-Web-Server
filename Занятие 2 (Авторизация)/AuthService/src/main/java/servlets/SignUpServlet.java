package servlets;

import accounts.AccountService;

import javax.servlet.http.HttpServlet;

public class SignUpServlet extends HttpServlet {

    /** Ссылка на {@link accounts.AccountService} */
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }
}
