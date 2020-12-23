public class CustomrControl {

    /**
     * 客户总数（非数组长度）
     */
    private int total = 0;
    Customer[] customer;

    /**
     * 获得客户组内保存有的客户数（非数组长度）
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * 构造器指定客户组的最大人数
     */
    CustomrControl(int length) {
        customer = new Customer[length];
        customer[0] = new Customer("屠董", "男", 2, "33", "22");
        customer[1] = new Customer("飞洒发生", "男", 2, "33", "22");
        customer[2] = new Customer("麻花疼", "男", 22, "3fsda3", "2fsa2");
        customer[3] = new Customer("叽里呱啦", "男", 2, "3fasd3", "2fdsa2");
        customer[4] = new Customer("ea`fsde", "男", 2, "3fasd3", "22");
        customer[5] = new Customer("ee2`1", "男", 2, "33", "22");
        total = 6;
    }

    //CRUD
    //CRUD分别指增加(Create) 、读取查询(Retrieve)、 更新(Update) 和删除(Delete)

    /**
     * (Create)增加客户  增加成功返回true  增加失败返回false
     */
    public boolean addCustomer(Customer customer) {
        if (total < this.customer.length) {
            this.customer[total++] = customer;
            return true;
        }
        return false;
    }

    /**
     * (Delete))删除客户  删除成功返回true  删除失败返回false
     */
    public boolean deleteCustomer(int who) {
        if (who > total || total == 0) {
            return false;
        }
        for (; who < total; who++) {
            customer[who - 1] = customer[who];
        }
        customer[--total] = null;
        return true;
    }

    /**
     * (Update)更改客户信息  更改成功返回true  更改失败返回false
     */
    public boolean updateCustomer(int who) {
        if (who > total || total == 0) {
            return false;
        }
        String t = InputTool.case0(false);
        if (t == "密码正确，不需要该值") { }
        else {
            customer[who - 1].setName(t);
        }

        t = InputTool.case1(false);
        if (t == "密码正确，不需要该值") { }
        else {
            customer[who - 1].setGender(t);
        }

        t = InputTool.scannerGetAge(false);
        if (t == "密码正确，不需要该值") { }
        else {
            customer[who - 1].setAge(Integer.valueOf(t));
        }

        t = InputTool.case2(false);
        if (t == "密码正确，不需要该值") { }
        else {
            customer[who - 1].setPhone(t);
        }

        t = InputTool.case3(false);
        if (t == "密码正确，不需要该值") { }
        else {
            customer[who - 1].setEmail(t);
        }

        return true;
    }


    /**
     * (Retrieve)查询客户信息  查询成功返回true  查询失败返回false
     */
    public boolean retrieveCustomer() {
        String t = "";
        int error = -1;
        if (total <= 0) {
            System.out.println("查询失败，原因：未找到客户");
            return true;
        }
        System.out.println("\n-------------------------------------客户列表-------------------------------------");
        System.out.println("编号" + "\t\t\t" + "姓名" + "\t\t\t" + "性别" + "\t\t\t" + "年龄" + "\t\t\t"
                + "电话" + "\t\t\t" + "邮箱");

        //若出错则抓异常，好让我知道哪里错
        try {
            for (int i = 0; i < total; i++) {
                error = i;

                //方法一，不会出现信息不对齐的情况，但代码冗余
                stringFormat(String.valueOf(i + 1));
                stringFormat(customer[i].getName());
                stringFormat(customer[i].getGender());
                stringFormat(String.valueOf(customer[i].getAge()));
                stringFormat(customer[i].getPhone());
                stringFormat(customer[i].getEmail());
                System.out.println();

                //方法二  会出现信息输出不对齐的情况
                //    System.out.println(String.valueOf(i + 1) + "\t\t\t" + customer[i].getName() + "\t\t\t"
                //           + customer[i].getGender() + "\t\t\t" + customer[i].getAge() + "\t\t\t"
                //           + customer[i].getPhone() + "\t\t\t" + customer[i].getEmail());
            }
        } catch (NullPointerException e) {
            System.out.println("查询时出错，空指针错误,在 customer[" + error + "] 位置没有对象");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("查询时出错，数组角标越界");
        } catch (Exception e) {
            System.out.println("查询时出现未知错误");
        }
        System.out.println("------------------------------------客户列表完成-------------------------------------\n");
        return true;
    }


    /**
     * 将客户的信息格式化输出，防止上方的 (方式二) 会出现不对齐的情况，但若是有中文，还是会有稍微的不对齐，暂未找到更好的方法
     *
     * @param s
     */
    private void stringFormat(String s) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > 128 || s.charAt(i) < 0) {
                a += 1;
                if (b == 0) {
                    b = 1;
                }
            }
        }
        //上面的for检测中文，若有中文，则下面的for少添加一个空格
        for (; ; ) {
            if (s.length() <= 15 - a + b) {
                s = s + " ";
                continue;
            }
            System.out.print(s);
            return;
        }
    }

}

