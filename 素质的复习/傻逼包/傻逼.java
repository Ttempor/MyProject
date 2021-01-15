package 傻逼包;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class 傻逼 {
    class 傻中傻 {
        {
            this.傻逼 = "傻逼代码块";
        }

        String 傻逼;

        傻中傻(String 傻逼) {
            傻逼 = 傻逼.this.傻逼;
        }
    }

    volatile static public 傻逼 傻逼一个 = new 傻逼("傻逼包.傻逼");
    String 傻逼 = "你傻逼傻逼";

    傻逼(String 傻逼) {
        傻逼 = 傻逼;
    }

    synchronized 傻逼 傻逼傻逼傻逼() throws 傻逼异常 {
        new 傻中傻("傻逼包.傻逼");
        System.out.println(傻逼);
        return new 傻逼("傻逼包.傻逼");
    }

    static 傻逼 获得傻逼一个() {
        return 傻逼一个;
    }

    public static void main(String[] args) throws Exception {
        new 傻逼("傻逼包.傻逼").傻逼傻逼傻逼();
    }
}

class 傻逼儿子 extends 傻逼 implements 傻逼接口 {
    傻逼儿子(傻逼 傻1) {
        super(傻1.傻逼);
    }

    傻逼 傻逼傻逼傻逼(傻逼 憨憨, 傻逼... 无数个傻逼) {
        synchronized (傻逼.class) {
            return 傻吧傻(傻了());
        }
    }

    傻逼 傻逼傻逼傻逼() {
        return 傻吧傻(傻了());
    }

    傻逼包.傻逼[] 一堆傻逼[] = new 傻逼儿子[5][5];

    public 傻逼 傻了() {
        try {
            throw new 傻逼异常("傻逼出毛病了");
        } catch (傻逼包.傻逼异常 傻逼异常) {
            System.out.println(傻逼异常.getMessage());
        } finally {
            return 获得傻逼一个();
        }
    }

}

interface 傻逼接口 {
    public static final String 傻逼 = "傻不傻";

    default 傻逼 傻吧傻(傻逼 真傻) {
        return 傻逼包.傻逼.傻逼一个;
    }

    public abstract 傻逼 傻了();

    public static 傻逼 你傻了() {
        return 傻逼包.傻逼.获得傻逼一个();
    }
}

class 傻逼异常 extends Exception {
    傻逼异常(String 傻逼) {
        super(傻逼);
    }
}

class 同时傻逼 extends Thread {
    @Override
    public void run() {
        try {
            new 傻1(new 傻2()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    Thread 同时傻逼1 = new Thread(() -> {
        run();
    });
    Thread 同时傻批2 = new Thread(new 傻1(new 傻2()));
}

class 傻1 extends FutureTask {

    public 傻1(Callable callable) {
        super(callable);
    }
}

class 傻2 implements Callable {
    @Override
    public 傻逼 call() throws Exception {
        return 傻逼.获得傻逼一个();
    }
}

