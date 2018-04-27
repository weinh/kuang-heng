package jvm.gc;

/**
 * <p>类名称：ReferenceCountingGC
 * <p>描述说明：
 * <p>作者单位：恒生芸擎网络有限公司
 * <p>版本号：2.0.0.0
 *
 * @author weinh
 * @date 2018-04-26 19:55
 */
public class ReferenceCountingGC {
    private byte b[] = new byte[1024 * 1024 * 2];
    private ReferenceCountingGC instance;

    /**
     * 运行时，加入jvm参数查看GC情况：-XX:+PrintGCDetails
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ReferenceCountingGC refA = new ReferenceCountingGC();
        ReferenceCountingGC refB = new ReferenceCountingGC();
        refA.instance = refB;
        refB.instance = refA;

        refA = null;
        refB = null;
        System.gc();
    }
}
