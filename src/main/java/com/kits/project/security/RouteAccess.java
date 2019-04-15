package com.kits.project.security;

public class RouteAccess {
    public String method;

    public String url;

    public RouteAccess(String url, String method) {
        this.method = method;
        this.url = url;
    }
}
