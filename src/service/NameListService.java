package service;

import domain.*;

public class NameListService {

    /**
     * 数组存储员工信息
     */
    private Employee[] employees;

    private Equipment creat(String s, int i) {
        switch (s) {
            case "21": {
                return new PC(Data.EQIPMENTS[i][1], Data.EQIPMENTS[i][2]);
            }
            case "22": {
                return new NoteBook(Data.EQIPMENTS[i][1], Double.parseDouble(Data.EQIPMENTS[i][2]));
            }
            case "23": {
                return new Printer(Data.EQIPMENTS[i][1], Data.EQIPMENTS[i][2]);
            }
        }
        System.out.println("出错了");
        return null;
    }

    public NameListService() {
        employees = new Employee[Data.EMPLOYEES.length];
        for (int i = 0; i < employees.length; i++) {
            int id = Integer.parseInt(service.Data.EMPLOYEES[i][1]);
            String name = service.Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(service.Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(service.Data.EMPLOYEES[i][4]);

            //Employee  :  10, id, name, age, salary
            //Programmer:  11, id, name, age, salary
            //Designer  :  12, id, name, age, salary, bonus
            //Architect :  13, id, name, age, salary, bonus, stock
            switch (service.Data.EMPLOYEES[i][0]) {
                case "10": {
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                }
                case "11": {
                    employees[i] = new Programmer(id, name, age, salary, creat(Data.EQIPMENTS[i][0], i));
                    break;
                }
                case "12": {
                    double bonus = Double.parseDouble(service.Data.EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, creat(Data.EQIPMENTS[i][0], i), bonus);

                    break;
                }
                case "13": {
                    double bonus = Double.parseDouble(service.Data.EMPLOYEES[i][5]);
                    int stock = Integer.parseInt(service.Data.EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, creat(Data.EQIPMENTS[i][0], i), bonus, stock);
                    break;
                }
            }
        }
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException {
        try {
            return employees[id];
        }
        catch (Exception e) {
            throw new TeamException();
        }
    }

}
