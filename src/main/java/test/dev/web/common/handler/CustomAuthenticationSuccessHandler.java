package test.dev.web.common.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author skkim
 * @since 2021-04-23
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    //인증이 성공될 경우 처리
    //세션 성공하여 반환된 Authentication 객체를 SecurityContextHolder의 Contetx에 저장해주어야 한다.
    //나중에 사용자의 정보를 꺼낼 경우에도 SecurityContextHolder의 context에서 조회하면 된다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.sendRedirect("/");
    }

}