package login.model.service;

import login.model.bean.Account;

public interface ILoginService {
    Account login(String user, String pass);
}
