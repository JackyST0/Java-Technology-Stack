package cn.itcast.hotel.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author JianXin
 * @Date 2023/1/9 15:14
 * @Github https://github.com/JackyST0
 */

@Data
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;

    public PageResult() {
    }

    public PageResult(Long total, List<HotelDoc> hotels) {
        this.total = total;
        this.hotels = hotels;
    }
}
