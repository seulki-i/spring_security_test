package test.dev.web.user.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author skkim
 * @since 2021-04-21
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
@Getter @Setter
public class UserInfo implements Serializable {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "department_name")
    private String departmentName;

//    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
//    private UserRole role; //권한여부

    @OneToMany(mappedBy = "userRoleKey.userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> roles; //권한여부

    @Column(name = "is_enable")
    private Boolean isEnable; // 사용 여부

    @Builder
    public UserInfo(String userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
