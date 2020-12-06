package com.faksoy.stocktracking.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserUtils {


    public static User getUser(){
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
