package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface LinkService {
    Link findByLinkAddress(String link_address);
    void save(Link link);
}
