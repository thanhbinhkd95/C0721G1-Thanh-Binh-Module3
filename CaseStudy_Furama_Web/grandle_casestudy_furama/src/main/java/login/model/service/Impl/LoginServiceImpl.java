package login.model.service.Impl;

import login.model.bean.Account;
import login.model.repository.LoginRepository;
import login.model.service.ILoginService;

public class LoginServiceImpl implements ILoginService {
    LoginRepository loginRepository = new LoginRepository();
    @Override
    public Account login(String user, String pass) {
        return loginRepository.login(user,pass);
    }
}
