package servlets;

import accounts.AccountService;

import javax.servlet.http.HttpServlet;

public abstract class AccountServlet extends HttpServlet {

    /** Ссылка на {@link accounts.AccountService} */
    protected final AccountService accountService;

    public AccountServlet(AccountService accountService) {
        this.accountService = accountService;
    }
}
