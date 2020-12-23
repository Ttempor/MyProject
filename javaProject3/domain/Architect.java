package domain;

public class Architect extends Designer {
    /**
     * 公司奖励的股票数量
     */
    private int stock;
    public int getstock() {
        return stock;
    }

    /**
     * 构造器，初始化架构师的信息
     * @param id
     * @param name
     * @param age
     * @param salary
     * @param stock
     */
    public Architect(int id, String name, int age, double salary,Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary,equipment, bonus);
        this.stock = stock;
    }
}
