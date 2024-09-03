package com.pushforward.testsecurity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PetController {
    @GetMapping("/pet")
    public String pet() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>" + SecurityContextHolder.getContext().getAuthentication());
        return "Kira";
    }
}
