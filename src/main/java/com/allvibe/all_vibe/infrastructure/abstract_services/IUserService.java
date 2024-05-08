package com.allvibe.all_vibe.infrastructure.abstract_services;

import com.allvibe.all_vibe.api.dto.request.UserRequest;
import com.allvibe.all_vibe.api.dto.response.UserResponse;

public interface IUserService extends CrudService<UserRequest,UserResponse,Long>{

    public final String FIELD_BY_SORT = "username";
}
