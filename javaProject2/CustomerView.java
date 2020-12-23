public class CustomerView {
    CustomrControl customrControl;
    /**
     * 构造器初始化，传入要控制的客户组
     */
    CustomerView(CustomrControl customrControl) {
        this.customrControl = customrControl;
    }
    public void mainView() {
        int selectInt = 0;
        while(true) {
            menu();
            select();
            selectInt = InputTool.scannerSelect();
            switch (selectInt) {
                case 1 : {
                    if (customrControl.addCustomer(new Customer(InputTool.scannerCreate(), InputTool.scannerCreate()
                    ,Integer.valueOf(InputTool.scannerGetAge(true)), InputTool.scannerCreate(), InputTool.scannerCreate()))) {
                        System.out.println("增加成功，目前客户数为 " + customrControl.getTotal());
                    } else {
                        System.out.println("未知原因导致增加失败");
                    }
//                    System.out.println("debug 1增加");
                    break;
                }
                case 2 : {
                    if (customrControl.updateCustomer(InputTool.scannerUpdate(customrControl.getTotal()))) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("修改失败");
                    }
//                  System.out.println("debug 2修改");
                    break;
                }
                case 3 : {
                    System.out.println("请输入整数 1 ~ " + customrControl.getTotal() + "之内以删除客户");
                    if(!customrControl.deleteCustomer(InputTool.scannerDeleteCustomer(customrControl.getTotal())))  {
                        System.out.println("某种未知原因,导致删除失败");
                    } else {
                        System.out.println("删除成功");
                    }
                    break;
                }
                case 4 : {
                    if(!customrControl.retrieveCustomer()) {
                        System.out.println("查询失败");
                    }
                    break;
                }
                case 5 : {
                    System.out.println("退出成功");
                    return;
                }
            }

        }
    }
    public void menu() {
        System.out.println("             -----------------客户信息管理软件-----------------\n");
        System.out.println("                              1 添 加 客 户");
        System.out.println("                              2 修 改 客 户");
        System.out.println("                              3 删 除 客 户");
        System.out.println("                              4 客 户 列 表");
        System.out.println("                              5 退      出\n");
    }
    public void select() {
        System.out.print("                              请选择(1-5)：");
    }
}
