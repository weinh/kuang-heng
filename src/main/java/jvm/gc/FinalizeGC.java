package jvm.gc;

/**
 * <p>类 名 称：FinalizeGC.java</p>
 * <p>功能说明：</p>
 * <p>开发时间：2018年04月24日</p>
 *
 * @author ：weinh
 */
public class FinalizeGC {
    private static FinalizeGC SAVA_HOOK = null;

    public void isAlive() {
        System.out.println("我还活着！");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize方法执行，逃过一劫");
        FinalizeGC.SAVA_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVA_HOOK = new FinalizeGC();

        SAVA_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVA_HOOK != null) {
            SAVA_HOOK.isAlive();
        } else {
            System.out.println("终于死了！");
        }

        SAVA_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVA_HOOK != null) {
            SAVA_HOOK.isAlive();
        } else {
            System.out.println("终于死了！");
        }
    }
}
