package com.kits.project.services.interfaces;

import com.kits.project.model.Authority;

public interface AuthorityService {
    Authority findByName(String name);
}
