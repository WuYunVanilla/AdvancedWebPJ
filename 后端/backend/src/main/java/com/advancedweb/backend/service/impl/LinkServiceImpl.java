package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.Link;
import com.advancedweb.backend.repository.LinkRepository;
import com.advancedweb.backend.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Link")
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Override
    public Link findByLinkAddress(String link_address) {
        return linkRepository.findByLinkAddress(link_address);
    }

    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }
}
