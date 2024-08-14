package com.entity.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    int getId();
    String getName();
    String getEmail();
    @Value("#{target.name + ' <' + target.email + '>'}")
    String getFullInfo();
}
