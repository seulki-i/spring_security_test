package test.dev.web.common.exception;

/**
 * @author skkim
 * @since 2021-04-21
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId){
        super(userId + "존재하지 않는 사용자입니다.");
    }
}
