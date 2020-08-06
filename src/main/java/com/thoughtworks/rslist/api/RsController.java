package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.RsEvent;
import com.thoughtworks.rslist.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RsController {
    public static List<RsEvent> rsEventList;

    public static List<RsEvent> initRsEventList() {
        rsEventList = new ArrayList<>();
        User admin = new User("admin", "female", 22, "a@b.com", "12345678900");
        rsEventList.add(new RsEvent("第一条事件", "无标签", admin));
        rsEventList.add(new RsEvent("第二条事件", "无标签", admin));
        rsEventList.add(new RsEvent("第三条事件", "无标签", admin));
        return rsEventList;
    }

    @GetMapping("/rs/{index}")
    public ResponseEntity getRsEvent(@PathVariable int index) {
        return ResponseEntity.ok(rsEventList.get(index - 1));
    }

    @GetMapping("/rs/list")
    public ResponseEntity getRsEventFromScope(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer end) {
        if (start != null || end != null) {
            return ResponseEntity.ok(rsEventList.subList(start - 1, end));
        }

        return ResponseEntity.ok(rsEventList);
    }

    @PostMapping("/rs/event")
    public ResponseEntity addRsEvent(@RequestBody @Valid RsEvent rsEvent) {
        rsEventList.add(rsEvent);
        if (!isUserExist(rsEvent.getUser())) {
            UserController.userList.add(rsEvent.getUser());
        }
        return ResponseEntity.ok(null);
    }

    private boolean isUserExist(User user) {
        for (User existedUser : UserController.userList) {
            if (user.getUserName().equals(existedUser.getUserName())) {
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/rs/delete{index}")
    public ResponseEntity deleteRsEvent(@PathVariable int index) {
        rsEventList.remove(index - 1);
        return ResponseEntity.ok(null);
    }

    @PutMapping("rs/update{index}")
    public ResponseEntity updateRsEvent(@PathVariable int index, @RequestBody RsEvent rsEvent) {
        RsEvent current = rsEventList.get(index - 1);
        String newEventName = rsEvent.getEventName();
        String newKeyWord = rsEvent.getKeyWord();
        if (newEventName != null && !newEventName.isEmpty()) {
            current.setEventName(newEventName);
        }

        if (newKeyWord != null && !newKeyWord.isEmpty()) {
            current.setKeyWord(newKeyWord);
        }
        return ResponseEntity.ok(null);
    }
}
