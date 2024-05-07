package com.allvibe.all_vibe.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

public interface CrudService<RQ,RS,ID> {
    
    public Page<RS> findAll(int page, int size);

    public RS findByIdWithDetails(ID id);

    public RS create(RQ request);

    public RS update(RQ request, ID id);

    public void delete(ID id);
}
