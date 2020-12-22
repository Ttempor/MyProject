package domain;

public class Designer extends Programmer {
    /**
     * 奖金
     */
    private double bonus;

    public double getBonus() {
        return bonus;
    }

    /**
     * 构造器初始化设计师信息
     * @param id
     * @param name
     * @param age
     * @param salary
     * @param bonus
     */
    public Designer(int id, String name, int age, double salary,
                    Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }
}
