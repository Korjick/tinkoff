package edu.hw2.Task4;

public final class Utils {

    private Utils() { }

    public static CallingInfo callingInfo(Throwable e) {
        StackTraceElement[] trace = e.getStackTrace();
        StackTraceElement last = trace[trace.length - 1];
        return new CallingInfo(last.getClassName(), last.getMethodName());
    }

    public record CallingInfo(String className, String methodName) { }
}
