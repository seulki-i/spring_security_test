package test.dev.web.user.service;

import lombok.RequiredArgsConstructor;
import test.dev.web.user.dao.UserInfoRepository;
import test.dev.web.user.dto.UserDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author skkim
 * @since 2021-04-21
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDTO loadUserByUsername(String userId) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userInfoRepository.findByUserId(userId)
                .map(u -> new UserDTO(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue()))))
                .filter(v -> v.getIsEnable().equals(Boolean.TRUE))
                .orElseThrow(() -> new UsernameNotFoundException((userId)));
    }
}
