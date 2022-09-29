package com.example.task_manager.entity;


import com.example.task_manager.entity.enums.PermissionEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, password, email,phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roleList; //user_roleList

    @OneToMany
    private List<Task> taskList;

    @Transient //bazada column ochilmaydi frontga qaytaradi
//    private int age = LocalDate.now().getYear() -birthDate.getYear();

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(String username, String password, Set<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //hasAuthority
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        for (Role role : this.roleList) {
            for (PermissionEnum permissionEnum : role.getPermissionEnum()) {
                authorities.add(new SimpleGrantedAuthority(permissionEnum.name()));
            }
        }

        //preauthorize hasRole
//        for (Role role : this.roleList) {
//            authorities.add(role.getRoleName());
//        }
        return authorities;
    }
}
