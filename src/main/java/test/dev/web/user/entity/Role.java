package test.dev.web.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author skkim
 * @since 2021-08-21
 */
@Entity
@Table(name = "role")
@Getter @Setter
@NoArgsConstructor
public class Role {

    public Role(String roleCode) {
        this.roleCode = roleCode;
    }

    @Id
    @Column
    private String roleCode;

    @Column
    private String roleName;
}
