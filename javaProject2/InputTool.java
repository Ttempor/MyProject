import java.util.Scanner;

public class InputTool {
    private static Scanner scanner = new Scanner(System.in);
    private static int i = 0;
    //  String name, String gender, int age, String phone, String email;

    /**
     * 获得一个岁数
     *
     * @return
     */
    public static String scannerGetAge(boolean bool) {
        System.out.print("请输入整数以代表岁数，岁数范围在1~100之间:");
        String t = "";
        int temp = -5;
        for (; ; ) {
            try {
                if (!bool) {
                    t = readKeyboard(3, "请输入整数以代表岁数，岁数范围在1~100之间", true);
                    if (t == "密码正确，不需要该值") {
                        return "密码正确，不需要该值";
                    }
                    boolean bool1 = true;
                    for (int i = 0; i < t.length(); i++) {
                        if (t.charAt(i) > 57 || t.charAt(i) < 48) {
                            bool1 = false;
                            break;
                        }
                    }
                    if (!bool1) {
                        System.out.println("请输入整数以代表岁数，岁数范围在1~100之间");
                        continue;
                    }
                    temp = Integer.parseInt(t);
                } else {
                    temp = scanner.nextInt();
                    t = scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("未知原因，抛出了异常，需要来这里修改代码");
                scanner.nextLine();//若输入了字母，则会无限循环，这里是吃掉有字母的情况，以防止无限循环
            }
            if (temp <= 0) {
                System.out.println("请输入数字0以上，岁数范围在1~100之间");
                continue;
            } else if (temp > 100) {
                System.out.println("请输入数字100以内，岁数范围在1~100之间");
                continue;
            }
            return t;
        }

    }

    /**
     * 增
     * 增加一个客户
     *
     * @return
     */
    public static String scannerCreate() {
        if (i >= 4 || i < 0) {
            i = 0;
        }
        switch (i) {
            case 0: {
                return case0(true);
            }
            case 1: {
                return case1(true);
            }

            case 2: {
                return case2(true);
            }
            case 3: {
                return case3(true);
            }
            default: {
                System.out.println("未知错误，导致返回默认值");
                return "错误名字";
            }
        }
    }

    public static String case0(boolean bool) {
        String temp = "";
        System.out.print("请输入姓名,可以瞎写:");
        i++;
        if (!bool) {
            temp = readKeyboard(10, "姓名被限制在10个字以内,请重新输入:", true);
            if (temp == "密码正确，不需要该值") {
                return temp;
            }
            return temp;
        }
        return readKeyboard(10, "姓名被限制在10个字以内,请重新输入:");
    }

    public static String case1(boolean bool) {
        String temp = "";
        System.out.print("请输入性别<男>或<女>:");
        i++;
        for (; ; ) {
            if (!bool) {
                temp = readKeyboard(1, "请输入‘男’或‘女’:", true);
                if (temp == "密码正确，不需要该值") {
                    return "密码正确，不需要该值";
                }
            } else {
                temp = readKeyboard(1, "请输入‘男’或‘女’:");
            }
            if (temp.charAt(0) == '男' || temp.charAt(0) == '女') {
                return temp;
            }
            System.out.print("请输入‘男’或‘女’:");
        }
    }

    public static String case2(boolean bool) {

        String temp = "";
        System.out.print("请输入电话:");
        i++;
        for (; ; ) {
            if (!bool) {
                temp = readKeyboard(11, "手机号码被限制在11位以内,请重新输入数字:", true);
                if (temp == "密码正确，不需要该值") {
                    return "密码正确，不需要该值";
                }
            } else {
                temp = readKeyboard(11, "手机号码被限制在11位以内,请重新输入数字:");
            }
            if (temp.charAt(0) != '1') {
                System.out.print("手机号码必须是1开头 -> ");
                continue;
            } else if (temp.length() != 11) {
                System.out.print("手机号码要求是11位数字 -> ");
                continue;
            }
            boolean bool1 = true;
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) < 48 || temp.charAt(i) > 57) {
                    System.out.print("手机号码要求是11位数字 -> ");
                    bool1 = false;
                    break;
                }
            }
            if (bool1) {
                return temp;
            }
        }
    }

    public static String case3(boolean bool) {
        String temp = "";
        System.out.print("邮箱,随便写吧,不做限制:");
        i++;
        if (!bool) {
            temp = readKeyboard(15, "邮箱填写不能超过15个字，请重写输入:", true);
            if (temp == "密码正确，不需要该值") {
                return "密码正确，不需要该值";
            }
            return temp;
        }
        return readKeyboard(15, "邮箱填写不能超过15个字，请重写输入:");
    }

    /**
     * 删
     * 返回要删除指定客户的位置，返回值不对应元素下标,需减一
     *
     * @param length
     * @return
     */
    public static int scannerDeleteCustomer(int length) {
        String temp;
        int tempInt = 0;
        while (true) {
            temp = readKeyboard((length < 10) ? 1 : 2, "请输入整数在 1 ~ " + length + "之内才能以删除客户");
            try {
                tempInt = Integer.parseInt(temp);
            } catch (Exception e) {
                System.out.println("请输入整数在 1 ~ " + length + "之内才能以删除客户");
                continue;
            }
            if (0 < tempInt && tempInt <= length) {
                return tempInt;
            }
            System.out.println("请输入整数在 1 ~ " + length + "之内才能以删除客户");
        }
    }

    /**
     * 改
     * 返回需要需改的客户位置
     */
    public static int scannerUpdate(int length) {
        System.out.println("请输入整数在 1 ~ " + length + "之内才能够修改客户");
        String temp;
        int tempInt = 0;
        while (true) {
            temp = readKeyboard((length < 10) ? 1 : 2, "请输入整数在 1 ~ " + length + "之内才能够修改客户");
            try {
                tempInt = Integer.parseInt(temp);
            } catch (Exception e) {
                System.out.println("请输入整数在 1 ~ " + length + "之内才能够修改客户");
                continue;
            }
            if (0 < tempInt && tempInt <= length) {
                return tempInt;
            }
            System.out.println("请输入整数在 1 ~ " + length + "之内才能够修改客户");
        }
    }

    /**
     * 获得1到5之间的整数
     *
     * @return
     */
    public static int scannerSelect() {
        String temp;
        while (true) {
            temp = readKeyboard(1, "请输入整数 1 ~ 5");
            if (temp.charAt(0) == '1' || temp.charAt(0) == '2' || temp.charAt(0) == '3'
                    || temp.charAt(0) == '4' || temp.charAt(0) == '5') {
                int tempInt = 0;
                /**
                 * 防止输入单个字母，虽然上面if的()里面已经解决了问题，可我是写完try catch才知道()里面已经解决了。。。
                 */
                try {
                    tempInt = Integer.parseInt(String.valueOf(temp.charAt(0)));
                } catch (Exception e) {
                    System.out.println("请输入整数 1 ~ 5");
                    continue;
                }
                return tempInt;
            }
            System.out.println("请输入整数 1 ~ 5");
        }
    }

    private static String readKeyboard(int limitLength, String describe) {
        String temp = "";

        //Scanner.hasNextLine() 检测下一行有没有输入
        while (scanner.hasNextLine()) {

            //nextLine() 把指针移动到下一行 让然后取出当前这一行的输入
            temp = scanner.nextLine();
//            System.out.println("temp的长度是" + temp.length());
            if (temp.length() >= 15) {
                System.out.print("硬性规定，输入长度要在15个字之内,请重新输入：");
                continue;
            } else if (temp.length() <= 0 || temp.length() > limitLength) {
                System.out.print(describe);
                continue;
            }
            break;
        }
        return temp;
    }

    private static String readKeyboard(int limitLength, String describe, boolean bool) {
        String temp = "";

        //Scanner.hasNextLine() 检测下一行有没有输入
        while (scanner.hasNextLine()) {

            //nextLine() 把指针移动到下一行 让然后取出当前这一行的输入
            temp = scanner.nextLine();
            if (temp.length() == 0) {
                return "密码正确，不需要该值";
            }
//            System.out.println("temp的长度是" + temp.length());
            if (temp.length() >= 15) {
                System.out.print("硬性规定，输入长度要在15个字之内,请重新输入：");
                continue;
            } else if (temp.length() < 0 || temp.length() > limitLength) {
                System.out.print(describe);
                continue;
            }
            break;
        }
        return temp;
    }
}
