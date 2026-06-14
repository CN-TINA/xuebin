package org.example.service;

import org.example.dao.RoomDao;
import org.example.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;

    public boolean addRoom(Room room) {
        return roomDao.addRoom(room) > 0;
    }

    // 分页公式精细化计算
    public Map<String, Object> getRoomPage(String keyword, int pageNo, int pageSize) {
        int startRow = (pageNo - 1) * pageSize;
        int endRow = pageNo * pageSize;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", keyword);
        paramMap.put("startRow", startRow);
        paramMap.put("endRow", endRow);

        int totalCount = roomDao.selectTotalCount(paramMap);
        List<Room> list = roomDao.selectRoomPage(paramMap);
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("totalPage", totalPage == 0 ? 1 : totalPage);
        resultMap.put("pageNo", pageNo);
        return resultMap;
    }

    public Room getRoomById(Integer roomId) {
        return roomDao.selectRoomById(roomId);
    }

    public boolean updateRoom(Room room) {
        return roomDao.updateRoom(room) > 0;
    }

    public boolean deleteRoom(Integer roomId) {
        return roomDao.logicalDeleteRoom(roomId) > 0;
    }
}