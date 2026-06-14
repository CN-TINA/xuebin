package org.example.pojo;

import java.util.Date;

public class Room {
    private Integer roomId;      // 对应 room_id
    private String roomNo;       // 对应 room_no (房号)
    private String roomType;     // 对应 room_type (房型)
    private Double price;        // 对应 price (价格)
    private Integer status;      // 对应 status (0空闲, 1已入住)
    private Integer isdel;       // 逻辑删除
    private Date delDate;        // 删除时间

    // 生成的Getter和Setter方法

    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Integer getIsdel() { return isdel; }
    public void setIsdel(Integer isdel) { this.isdel = isdel; }

    public Date getDelDate() { return delDate; }
    public void setDelDate(Date delDate) { this.delDate = delDate; }
}