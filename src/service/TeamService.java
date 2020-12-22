package service;

import domain.Employee;
import domain.Programmer;
import view.TeamView;

public class TeamService {
    /**
     * TeamID计数
     */
    private int totalArchitect = 0;
    private int totalDesigner = 0;
    private int totalProgrammer = 0;
    private static int counter = 1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;

    public int getTotal() {
        return total;
    }

    public Programmer[] getTeam() {
        return team;
    }

    /**
     * 失败信息包含以下几种：
     * 成员已满，无法添加
     * 该成员不是开发人员，无法添加
     * 该员工已在本开发团队中
     * 该员工已是某团队成员
     * 该员正在休假，无法添加
     * 团队中至多只能有一名架构师
     * 团队中至多只能有两名设计师
     * 团队中至多只能有三名程序员
     *
     * @param e
     */
    public void addMember(Employee e, String position) throws TeamException {
        for (int i = 0; i < total; i++) {
            if (team[i] == e) {
                throw new TeamException("出错，人员重复，该人员已在团队中");
            }
        }
        if (total >= 5) {
            throw new TeamException("出错，人数已上限，最多5人");
        }
        if (!(e instanceof Programmer)) {
            throw new TeamException("出错，非打工人");
        }
        if (((Programmer) e).getStatus() != Status.FREE) {
            throw new TeamException("该员工状态不适合加入团队");
        }
        switch (position) {
            case "架构师": {
                if (totalArchitect >= 1) {
                    throw new TeamException("出错，最多能有3个架构师");
                }
                totalArchitect++;
                break;
            }
            case "设计师": {
                if (totalDesigner >= 2) {
                    throw new TeamException("出错，最多能有1个设计师");
                }
                totalDesigner++;
                break;
            }
            case "程序员": {
                if (totalProgrammer >= 3) {
                    throw new TeamException("出错，最多能有3个程序员");
                }
                totalProgrammer++;
                break;
            }
        }
        team[total++] = (Programmer) e;
        ((Programmer) e).setStatus(Status.BUSY);
        ((Programmer) e).setMemberId(counter++);
    }

    public void removeMember(int memberId) throws TeamException {
        boolean tempBool = true;
        for (int i = 0; i < total; i++) {
            if(team[i].getId() == memberId) {
                tempBool = false;
                memberId = i + 1;
                break;
            }
        }
        if (tempBool) {
            throw new TeamException("目标不存在");
        }
        team[memberId - 1].setStatus(Status.FREE);
        switch (TeamView.getPosition(team[memberId - 1])) {
            case "架构师": {
                totalArchitect--;
                break;
            }
            case "设计师": {
                totalDesigner--;
                break;
            }
            case "程序员": {
                totalProgrammer--;
                break;
            }
        }
        for (int i = memberId - 1; i < total - 1; i++) {
            team[i] = team[i + 1];
        }
        team[--total] = null;
    }
}
