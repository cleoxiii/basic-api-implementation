package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.RsEvent;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RsController {
  private static List<RsEvent> rsEventList;

  public static List<RsEvent> initRsEventList() {
      rsEventList = new ArrayList<>();
      rsEventList.add(new RsEvent("第一条事件", "无标签"));
      rsEventList.add(new RsEvent("第二条事件", "无标签"));
      rsEventList.add(new RsEvent("第三条事件", "无标签"));
      return rsEventList;
  }

  @GetMapping("/rs/{index}")
  public RsEvent getRsEvent(@PathVariable int index) {
    return rsEventList.get(index - 1);
  }

  @GetMapping("/rs/list")
  public List<RsEvent> getRsEventFromScope(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer end) {
    if (start != null || end != null) {
      return rsEventList.subList(start - 1, end);
    }

    return rsEventList;
  }

  @PostMapping("/rs/event")
  public void addRsEvent(@RequestBody RsEvent rsEvent) {
      rsEventList.add(rsEvent);
  }

  @DeleteMapping("/rs/delete{index}")
  public void deleteRsEvent(@PathVariable int index) {
      rsEventList.remove(index - 1);
  }

  @PutMapping("rs/update{index}")
  public void updateRsEvent(@PathVariable int index, @RequestBody RsEvent rsEvent) {
      RsEvent current = rsEventList.get(index - 1);
      String newEventName = rsEvent.getEventName();
      String newKeyWord = rsEvent.getKeyWord();
      if (!newEventName.isEmpty() && !newKeyWord.isEmpty()) {
          current.setEventName(newEventName);
          current.setKeyWord(newKeyWord);
      }
  }
}
