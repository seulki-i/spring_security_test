package test.dev.web.common.config.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import test.dev.web.user.dto.UserDTO;
import test.dev.web.user.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
/**
 * @author skkim
 * @since 2021-04-21
 */
@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "userService")
    private UserService userService;

    @NonNull
    private final BCryptPasswordEncoder passwordEncoder;

    //검증을 위한 구현
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userId = token.getName();
        String password = (String) token.getCredentials();
        //DB에서 아이디로 사용자 조회
        UserDTO userDTO = userService.loadUserByUsername(userId);

        if (!passwordEncoder.matches(password, passwordEncoder.encode(userDTO.getPassword()))) {
            throw new BadCredentialsException(userDTO.getUsername() + " 패스워드가 일치하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(userDTO, password, userDTO.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
