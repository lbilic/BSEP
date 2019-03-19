package com.kits.project.services.implementations;

import com.kits.project.model.Software;
import com.kits.project.repositories.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService implements com.kits.project.services.interfaces.SoftwareService {
    @Autowired
    private SoftwareRepository softwareRepository;

    @Override
    public List<Software> getConnectedSoftware(String nameId) {
        Software software = this.softwareRepository.findByNameId(nameId);
        return software.getConnectedSoftware();
    }
}
