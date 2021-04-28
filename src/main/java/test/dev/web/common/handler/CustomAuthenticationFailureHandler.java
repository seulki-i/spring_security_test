package test.dev.web.common.handler;

import lombok.Getter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author skkim
 * @since 2021-04-27
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Getter
    int errorCode;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

//        String loginId = request.getParameter("loginId");

        if (e instanceof InternalAuthenticationServiceException) { //로그인중 오류 발생
            errorCode = -1;
        } else if (e instanceof BadCredentialsException) { //패스워드 불일치
            errorCode = -2;
        } else if (e instanceof DisabledException) { //사용할 수 없는 아이디
            errorCode = -3;
//        } else if (e instanceof CredentialsExpiredException) { //비밀번호 만료
//            errorCode = -4;
//        } else if (e instanceof LockedException) { //잠긴 계정
//            errorCode = -5;
//        } else if (e instanceof AccountExpiredException) { //만료된 계정
//            errorCode = -6;
//        } else if (e instanceof SessionAuthenticationException) { //다른사용자가 로그인
//            errorCode = -7;
        } else if (e instanceof UsernameNotFoundException) {
            errorCode = -8;
        } else {
            errorCode = 0;
        }

        if ("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(String.format("{\"input\": null, \"inputList\": null, \"output\": {\"errorCode\": %d, \"redirectUrl\": null}, \"outputList\": null}", errorCode));
            printWriter.flush();
            printWriter.close();
        } else {
//            this.setDefaultFailureUrl("/login?logout&errorCode=" + errorCode +  "&loginId" + loginId);
            this.setDefaultFailureUrl("/login?logout&errorCode=" + errorCode);
            super.onAuthenticationFailure(request, response, e);
        }
    }
}
