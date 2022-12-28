package com.cos.dysson.specification;

import com.cos.dysson.model.RoleType;
import com.cos.dysson.model.Users;
import org.springframework.data.jpa.domain.Specification;

public class AdminSpecification {

    public static Specification<Users> searchTypeUsername(String searchKeyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + searchKeyword + "%");
    }

    public static Specification<Users> searchTypeName(String searchKeyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + searchKeyword + "%");
    }

    public static Specification<Users> userRole(RoleType roles) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("roles"), roles);
    }

}
