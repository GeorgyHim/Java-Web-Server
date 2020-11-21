package servlets;

import accounts.AccountService;

public class SignUpServlet extends AccountServlet {

    public SignUpServlet(AccountService accountService) {
        super(accountService);
    }
}
