package com.entity.projection;

import org.springframework.beans.factory.annotation.Value;

public interface DepartmentProjection {
    int getId();
    @Value("#{target.name + ' Department'}")
    String getName();
}
