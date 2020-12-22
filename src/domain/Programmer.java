package domain;

import service.Status;

public class Programmer extends Employee {
    /**
     * 团队ID
     */
    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment;

    /**
     * 构造器初始化程序员信息
     * @param id
     * @param name
     * @param age
     * @param salary
     * @param equipment
     */
    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.memberId = memberId;
        this.status = status;
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public Status getStatus() {
        return status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
