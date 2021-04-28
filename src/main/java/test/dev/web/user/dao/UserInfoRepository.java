package test.dev.web.user.dao;

import test.dev.web.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author skkim
 * @since 2021-04-21
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUserId(String userId);
}
