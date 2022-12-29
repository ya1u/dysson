package com.cos.dysson.controller;

import com.cos.dysson.config.auth.PrincipalDetail;
import com.cos.dysson.model.RoleType;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.OrderItemRepository;
import com.cos.dysson.repository.UserRepository;
import com.cos.dysson.service.ProductService;
import com.cos.dysson.specification.AdminSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/admin")
    public String admin(Model model,
                        @PageableDefault(size = 10,sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
                        @RequestParam(value = "category", defaultValue = "user") String category,
                        @RequestParam(value = "searchType", defaultValue = "") String searchType,
                        @RequestParam(value = "searchKeyword", required = false ) String searchKeyword,
                        @AuthenticationPrincipal PrincipalDetail principal) {

        Specification<Users> spec = ((root, query, criteriaBuilder) -> null);
        spec = spec.and(AdminSpecification.userRole(RoleType.user));

        if(category.equals("user")) {
            if(!searchType.isEmpty()) {
                if(searchType.equals("username")) {
                    spec = spec.and(AdminSpecification.searchTypeUsername(searchKeyword));
                } else {
                    spec = spec.and(AdminSpecification.searchTypeName(searchKeyword));
                }
            }

            model.addAttribute("users", userRepository.findAll(spec, pageable));
        }
        model.addAttribute("category", category);
        model.addAttribute("product", productService.제품리스트(pageable));
        return "admin/admin";
    }
}
