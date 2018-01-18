package rizki.practicum.learning.service.authorization;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rizki.practicum.learning.entity.MyUserDetails;

import java.util.Collection;

@Service
public class AuthorizationServiceImpl implements  AuthorizationService {
    @Override
    public MyUserDetails getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();
        return myUserDetails;
    }
}