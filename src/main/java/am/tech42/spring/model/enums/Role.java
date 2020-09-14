package am.tech42.spring.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    EMPLOYEE(Set.of(Permission.CV_WRITE,Permission.POST_READ,Permission.GET_EMPLOYEE)),
    COMPANY(Set.of(Permission.POST_WRITE, Permission.POST_READ,Permission.LOGO_ADD,Permission.GET_COMPANY));

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
