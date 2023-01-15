package cn.itcast.hotel.pojo;

import lombok.Data;

/**
 * @Author JianXin
 * @Date 2023/1/9 15:12
 * @Github https://github.com/JackyST0
 */

@Data
public class RequestParams {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String city;
    private String brand;
    private String starName;
    private Integer minPrice;
    private Integer maxPrice;
    private String location;
}
