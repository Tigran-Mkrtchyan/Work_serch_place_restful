package am.tech42.spring.model.enums;

public enum Permission {
    POST_READ("post:read"),
    POST_WRITE("post:write"),
    CV_WRITE("cv:write"),
    LOGO_ADD("logo:add"),
    GET_EMPLOYEE("get:employee"),
    GET_COMPANY("get:company");

    private final String permission;

    Permission(String permission) {

        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
