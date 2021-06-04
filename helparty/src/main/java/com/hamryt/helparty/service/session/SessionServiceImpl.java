package com.hamryt.helparty.service.session;

import com.hamryt.helparty.exception.login.NoLoginAuthException;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {
    
    private final HttpSession session;
    
    public void sessionValidate() {
        Long userId = (Long) session.getAttribute(SessionKeys.LOGIN_USER_ID);
        
        if (userId == null) {
            throw new NoLoginAuthException();
        }
    }
    
    public Long getLoginId() {
        return (Long) session.getAttribute(SessionKeys.LOGIN_USER_ID);
    }
    
}
