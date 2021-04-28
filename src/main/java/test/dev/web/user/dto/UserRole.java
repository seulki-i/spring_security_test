package test.dev.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author skkim
 * @since 2021-04-23
 */
@AllArgsConstructor
@Getter
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;
}
