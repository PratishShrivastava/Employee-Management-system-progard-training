package com.EmployeeManagementSystem.EmployeeManagementSystem.Models;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name="Employees")
public class Employees implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    @Column(nullable = false)
    private String employeeName;
    @Column(nullable = false)
    private String employeeAddress;
    @Column(nullable = false)
    private int employeePhoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String employeeSalary;

    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="employeeroles", joinColumns = @JoinColumn(name="Employees", referencedColumnName = "employeeId"),inverseJoinColumns = @JoinColumn(name="Role",referencedColumnName = "id"))
    private Set<Role> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}