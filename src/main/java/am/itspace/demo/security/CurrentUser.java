package am.itspace.demo.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private final am.itspace.demo.model.User user;

    public CurrentUser(am.itspace.demo.model.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public am.itspace.demo.model.User getUser() {
        return user;
    }
}
