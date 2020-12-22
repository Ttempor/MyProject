package view;

import domain.*;
import service.NameListService;
import service.TeamException;
import service.TeamService;

public class TeamView {
    String l = "ID        ";
    //公司所有成员
    private NameListService listSvc = new NameListService();
    //公司团队成员
    private TeamService teamSvc = new TeamService();

    public String trimLength(double i) {
        String s = String.valueOf(i);
        for (; ; ) {
            if (s.length() >= l.length()) {
                return s;
            }
            s += " ";
        }
    }

    public String trimLength(int i) {
        String s = String.valueOf(i);
        for (; ; ) {
            if (s.length() >= l.length()) {
                return s;
            }
            s += " ";
        }
    }

    public String trimLength(String s) {
        for (; ; ) {
            if (s.length() >= l.length()) {
                return s;
            }
            s += " ";
        }
    }

    /**
     * 主界面 显示及控制方法
     */
    public void enterMainMenu() throws Exception {
        addMember(2);
        addMember(3);
        addMember(9);
        addMember(0);
        addMember(10);
        addMember(9);
        addMember(10);
        addMember(45);
        addMember(1);
        addMember(1);
        addMember(4);
        addMember(1);
        int i = 0;
        while (true) {
            listAllEmployees();
            switch (TSUtility.readMenuSelection()) {
                case '1': {
                    getTeam();
                    TSUtility.readReturn();
                    continue;
                }
                case '2': {
                    boolean flag = true;
                    while (true) {
                        System.out.print("输入要添加的成员ID:");
                        if (!addMember(TSUtility.readInt() - 1)) {
                            System.out.print("请输入Y(重新添加)或N(返回原界面)：");
                            if (TSUtility.readConfirmSelection("请输入Y(重新添加)或N(返回原界面)：") == 'Y') {
                                continue;
                            }
                            flag = false;
                            break;
                        }
                        break;
                    }
                    if (flag) {
                        System.out.println("添加成功");
                    }
                    TSUtility.readReturn();
                    continue;
                }
                case '3': {
                    getTeam();
                    boolean flag = true;
                    while (true) {
                        System.out.print("输入要删除的成员ID:");
                        if (!deleteMember(TSUtility.readInt())) {
                            System.out.print("请输入Y(重新添加)或N(返回原界面)：");
                            if (TSUtility.readConfirmSelection("请输入Y(重新添加)或N(返回原界面)：") == 'Y') {
                                continue;
                            }
                            flag = false;
                            break;
                        }
                        break;
                    }
                    if (flag) {
                        System.out.println("删除成功");
                    }
                    TSUtility.readReturn();
                    continue;
                }
                case '4': {
                    System.out.print("请输入Y(确定退出)或N(返回原界面)：");
                    if (TSUtility.readConfirmSelection("请输入Y(确定退出)或N(返回原界面)：") == 'N') {
                        continue;
                    }
                    return;
                }
            }

            break;
        }
    }

    //PC      :21, model, display
    //NoteBook:22, model, price
    //Printer :23, type, name

    /**
     * 实现删除成员操作
     */
    private boolean deleteMember(int who) {
        try {
            teamSvc.removeMember(who);
        } catch (Exception e) {
            try {
                throw new TeamException("teamView添加出错，非打工人 或不存在");
            } catch (Exception t) {
                System.out.println(t.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * 实现添加成员操作
     */
    private boolean addMember(int who) {
        try {
            teamSvc.addMember(listSvc.getAllEmployees()[who], getPosition((Programmer) (listSvc.getAllEmployees()[who])));
        } catch (TeamException t) {
            System.out.println(t.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("添加出错，不知道怎么解决，但是，报错怎么了，try catch盖过不就行了？");
            return false;
        }
        return true;
    }

    public String getDescription(Equipment e) throws Exception {
        if (e instanceof PC) {
            return e.getDescription();
        }
        if (e instanceof NoteBook) {
            return e.getDescription();
        }
        if (e instanceof Printer) {
            return e.getDescription();
        }
        throw new TeamException();
    }

    /**
     * 以表格形式列出公司所有成员
     */
    private void listAllEmployees() throws Exception {
        System.out.println("-------------------------------------开发团队调度软件--------------------------------------");
        System.out.println(trimLength("ID") + trimLength("姓名") + trimLength("年龄") + trimLength("工资")
                + trimLength("职位") + trimLength("状态") + trimLength("奖金") + trimLength("股票")
                + trimLength(" 领用设备"));
        for (int i = 0; i < listSvc.getAllEmployees().length; i++) {
            if (listSvc.getEmployee(i) instanceof Architect) {
                Architect temp = (Architect) listSvc.getEmployee(i);
                System.out.println(trimLength(temp.getId()) + trimLength(temp.getName()) + trimLength(temp.getAge())
                        + trimLength(temp.getSalary()) + trimLength("架构师")
                        + trimLength(temp.getStatus().getNAME())
                        + trimLength(temp.getBonus()) + trimLength(temp.getstock()) + getDescription(temp.getEquipment()));
            } else if (listSvc.getEmployee(i) instanceof Designer) {
                Designer temp = (Designer) listSvc.getEmployee(i);
                System.out.println(trimLength(temp.getId()) + trimLength(temp.getName()) + trimLength(temp.getAge())
                        + trimLength(temp.getSalary()) + trimLength("设计师")
                        + trimLength(temp.getStatus().getNAME())
                        + trimLength(temp.getBonus()) + trimLength("") + getDescription(temp.getEquipment()));
            } else if (listSvc.getEmployee(i) instanceof Programmer) {
                Programmer temp = (Programmer) listSvc.getEmployee(i);
                System.out.println(trimLength(temp.getId()) + trimLength(temp.getName()) + trimLength(temp.getAge())
                        + trimLength(temp.getSalary()) + trimLength("程序员")
                        + trimLength(temp.getStatus().getNAME())
                        + trimLength("") + trimLength("") + getDescription(temp.getEquipment()));
            } else if (listSvc.getEmployee(i) instanceof Employee) {
                Employee temp = (Employee) listSvc.getEmployee(i);
                System.out.println(trimLength(temp.getId()) + trimLength(temp.getName()) + trimLength(temp.getAge())
                        + trimLength(temp.getSalary()));
            }
        }
        System.out.print("----------------------------------------------------------------------------------------\n");
        System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)： ");
    }

    /**
     * 显示团队成员列表
     */
    private void getTeam() {
        System.out.println("--------------------团队成员列表---------------------");
        System.out.println(trimLength("TDI/ID") + trimLength("姓名") + trimLength("年龄") + trimLength("工资")
                + trimLength("职位") + trimLength("奖金") + trimLength("股票"));
        for (int i = 0; i < teamSvc.getTotal(); i++) {
            Programmer p = teamSvc.getTeam()[i];
            System.out.println(trimLength(p.getMemberId() + "/" + p.getId()) + trimLength(p.getName())
                    + trimLength(p.getAge()) + trimLength(p.getSalary()) + trimLength(getPosition(p))
                    + trimLength(getBonus(p)) + getStock(p));
        }
    }

    public static String getPosition(Programmer p) {
        if (p instanceof Architect) {
            return "架构师";
        }
        if (p instanceof Designer) {
            return "设计师";
        }
        return "程序员";
    }

    /**
     * 获得股票
     *
     * @param p
     * @return
     */
    public String getStock(Programmer p) {
        if (getPosition(p) == "架构师") {
            return String.valueOf(((Architect) p).getstock());
        }
        return " ";
    }

    /**
     * 获得奖金
     *
     * @param p
     * @return
     */
    public String getBonus(Programmer p) {
        if (p instanceof Designer) {
            return ((Designer) p).getBonus() + "";
        }
        return "";
    }


    public static void main(String[] args) throws Exception {
        new TeamView().enterMainMenu();
    }
}
