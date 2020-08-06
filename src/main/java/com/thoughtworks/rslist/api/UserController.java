package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.RsEvent;
import com.thoughtworks.rslist.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    public static List<User> userList;

    public static List<User> initUserList() {
        userList = new ArrayList<>();
        User admin = new User("admin", "female", 22, "a@b.com", "12345678900");
        userList.add(admin);
        return userList;
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody @Valid User user) {
        userList.add(user);
    }

    @GetMapping("/user/list")
    public List<User> getUserList() { return userList; }
}
