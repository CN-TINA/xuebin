package org.example.controller;

import org.example.pojo.Room;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // 1录入新客房
    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String add(Room room) {
        boolean isSuccess = roomService.addRoom(room);
        if (isSuccess) {
            return "<script>alert('客房录入成功！'); window.location.href='../roomList.html';</script>";
        } else {
            return "客房录入失败。";
        }
    }

    // 2异步分页与检索接口
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                       @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
        int pageSize = 5;
        Map<String, Object> result = roomService.getRoomPage(keyword, pageNo, pageSize);

        List<Room> list = (List<Room>) result.get("list");
        int totalPage = (int) result.get("totalPage");

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"pageNo\":").append(pageNo).append(",");
        json.append("\"totalPage\":").append(totalPage).append(",");
        json.append("\"data\":[");
        for (int i = 0; i < list.size(); i++) {
            Room r = list.get(i);
            json.append("{");
            json.append("\"roomId\":").append(r.getRoomId()).append(",");
            json.append("\"roomNo\":\"").append(r.getRoomNo() != null ? r.getRoomNo() : "").append("\",");
            json.append("\"roomType\":\"").append(r.getRoomType() != null ? r.getRoomType() : "").append("\",");
            json.append("\"price\":").append(r.getPrice() != null ? r.getPrice() : 0.0).append(",");
            json.append("\"status\":").append(r.getStatus() != null ? r.getStatus() : 0);
            json.append("}");
            if (i < list.size() - 1) json.append(",");
        }
        json.append("]}");
        return json.toString();
    }

    // 3异步逻辑删除接口
    @RequestMapping(value = "/delete", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("roomId") Integer roomId) {
        boolean success = roomService.deleteRoom(roomId);
        return success ? "success" : "fail";
    }

    // 4数据回显接口
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String get(@RequestParam("roomId") Integer roomId) {
        Room r = roomService.getRoomById(roomId);
        if (r == null) return "{}";
        return String.format("{\"roomId\":%d,\"roomNo\":\"%s\",\"roomType\":\"%s\",\"price\":%.2f,\"status\":%d}",
                r.getRoomId(), r.getRoomNo(), r.getRoomType(), r.getPrice(), r.getStatus());
    }

    // 5保存修改接口
    @RequestMapping(value = "/update", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String update(Room room) {
        boolean success = roomService.updateRoom(room);
        return success ? "success" : "fail";
    }
}