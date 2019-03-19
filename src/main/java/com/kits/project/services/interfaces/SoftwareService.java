package com.kits.project.services.interfaces;

import com.kits.project.model.Software;

import java.util.List;

public interface SoftwareService {

    public List<Software> getConnectedSoftware(String nameId);
}
