package com.sk.manage.domain.system;

import com.sk.manage.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "system_user", indexes = @Index(name="idx_system_user", columnList = "system_id, user_id", unique = true))
@NoArgsConstructor
@ToString
@Getter
@Entity
public class SystemUser {

    private SystemUser(System system, User user){
        this.system = system;
        this.user = user;
    }

    @Id @GeneratedValue
    @Column(name = "system_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id")
    private System system;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    public static SystemUser createSystemUser(System system, User user){
        SystemUser su = new SystemUser(system, user);
        return su;
    }
}