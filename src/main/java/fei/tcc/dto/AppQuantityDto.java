package fei.tcc.dto;

/**
 * Created by thiagoretondar on 27/11/16.
 */
public class AppQuantityDto {

    private Integer id;

    private String appname;

    private Integer quantity;

    public AppQuantityDto() {
    }

    public AppQuantityDto(Integer id, String appname, Integer quantity) {
        this.id = id;
        this.appname = appname;
        this.quantity = quantity;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
