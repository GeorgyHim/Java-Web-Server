package servlets;

import accounts.AccountService;

public class SignInServlet extends AccountServlet {

    public SignInServlet(AccountService accountService) {
        super(accountService);
    }
}
