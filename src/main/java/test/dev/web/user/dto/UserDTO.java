package test.dev.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import test.dev.web.user.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author skkim
 * @since 2021-04-21
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements UserDetails {
    @Delegate
    private UserInfo userInfo;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        userInfo.getRoles().forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getUserRoleKey().getRole().getRoleCode())));

        return simpleGrantedAuthorities;
    }

    public UserDTO(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.authorities = getAuthorities();
    }

    @Override
    public String getPassword() {
        return userInfo.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userInfo.getIsEnable();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userInfo.getIsEnable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userInfo.getIsEnable();
    }

    @Override
    public boolean isEnabled() {
        return userInfo.getIsEnable();
    }
}
