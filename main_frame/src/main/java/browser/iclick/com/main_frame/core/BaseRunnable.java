package browser.iclick.com.main_frame.core;

/**
 * Created by bym on 2018/3/8.
 */

abstract class BaseRunnable implements Runnable {

    protected final String name;

    public BaseRunnable(String format, Object... args) {
        this.name = String.format(format, args);
    }

    @Override
    public void run() {
        //获取当前线程名称
        String oldName = Thread.currentThread().getName();
        //设置新名称
        Thread.currentThread().setName(name);
        try {
            //执行
            execute();
        } finally {
            //还原名称
            Thread.currentThread().setName(oldName);
        }
    }

    protected abstract void execute();

}
