package cn.liueleven.junit.core.exception;

/**
 * @description: 测试用例断言异常
 * @date: 2020-06-07 12:28
 * @author: 十一
 */
public class TestCaseAssertException extends RuntimeException{

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TestCaseAssertException(String message) {
        super(message);
    }
}
