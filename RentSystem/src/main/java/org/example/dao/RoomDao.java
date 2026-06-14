package org.example.dao;

import org.example.pojo.Room;
import java.util.List;
import java.util.Map;

public interface RoomDao {
    // 1增加客房信息
    int addRoom(Room room);

    // 2查询符合条件的客房总数（分页与模糊查询核心）
    int selectTotalCount(Map<String, Object> paramMap);

    // 3Oracle专用：分页 + 模糊查询客房列表
    List<Room> selectRoomPage(Map<String, Object> paramMap);

    // 4根据ID查询单个客房（修改数据回显专用）
    Room selectRoomById(Integer roomId);

    // 5修改客房基本信息
    int updateRoom(Room room);

    // 6逻辑删除（将isdel修改为0，并记录删除时间）
    int logicalDeleteRoom(Integer roomId);
}