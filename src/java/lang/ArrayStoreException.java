package java.lang;

/**
 * 试图将错误类型的对象存储到对象数组中时抛出异常。例如：
 * Object x[] = new String[3];
 * x[0] = new Integer(0);
 */
public
class ArrayStoreException extends RuntimeException {
    private static final long serialVersionUID = -4522193890499838241L;

    /**
     * 构造一个没有细节信息的ArrayStoreException。
     */
    public ArrayStoreException() {
        super();
    }

    /**
     * 用指定的细节信息构造一个ArrayStoreException。
     */
    public ArrayStoreException(String s) {
        super(s);
    }
}
