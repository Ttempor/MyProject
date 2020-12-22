package domain;

public class NoteBook implements Equipment {
    private String model;
    private double price;

    /**
     * 构造器初始化设备的信息
     * model 设备的名称
     * price 设备的价格
     */
    public NoteBook(String model, double price) {
        this.model = model;
        this.price = price;
    }

    /**
     * 实现设备接口，返回各自属性的信息
     */
    public String getDescription() {

        return model + "(" + price + ")";
    }
}
