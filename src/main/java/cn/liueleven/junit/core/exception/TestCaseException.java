package cn.liueleven.junit.core.exception;

/**
 * @description: 测试用例方法失败异常，这个异常是要捕捉的
 * @date: 2020-06-07 15:51
 * @author: 十一
 */
public class TestCaseException extends RuntimeException{


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TestCaseException(String message) {
        super(message);
    }
}
