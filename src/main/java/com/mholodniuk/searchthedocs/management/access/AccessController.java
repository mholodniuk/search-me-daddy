package com.mholodniuk.searchthedocs.management.access;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
@RequiredArgsConstructor
class AccessController {

    @GetMapping
    @PreAuthorize("@accessService.hasAccess(1, 2, 'VIEWER')")
    public void test() {
        System.out.println("dupa");
    }
}
