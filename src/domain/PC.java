package domain;

public class PC implements Equipment {
    private String model;
    private String display;
    /**
     * 构造器
     * 初始化设备的型号 model
     * 设备的名称 display
     * @param model
     * @param display
     */
    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }

    /**
     * 实现设备接口，返回各自属性的信息
     * @return
     */
    @Override
    public String getDescription() {
        return model + "(" + display + ")";
    }
}
