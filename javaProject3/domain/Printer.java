package domain;

public class Printer implements Equipment {
    private String name;
    private String type;

    /**
     * 构造器初始化设备的信息
     * name 设备的名称
     * type 设备的类型
     */
    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * 实现设备接口，返回各自属性的信息
     */
    public String getDescription() {
        return name + "(" + type + ")";
    }
}