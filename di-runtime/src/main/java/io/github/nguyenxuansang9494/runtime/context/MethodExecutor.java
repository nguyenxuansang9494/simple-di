package io.github.nguyenxuansang9494.runtime.context;

import io.github.nguyenxuansang9494.annotations.Runner;
import io.github.nguyenxuansang9494.runtime.exception.FailedToExecuteException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodExecutor {
    private final Object invokingObject;
    private final Method executedMethod;
    private final int priorityLevel;

    public Object getInvokingObject() {
        return invokingObject;
    }

    public Method getExecutedMethod() {
        return executedMethod;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public MethodExecutor(Method method, Object invokingObject) {
        Runner runner = method.getDeclaredAnnotation(Runner.class);
        int priority = runner.priority();
        this.executedMethod = method;
        this.invokingObject = invokingObject;
        this.priorityLevel = priority;
    }

    public void execute() {
        try {
            executedMethod.invoke(invokingObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new FailedToExecuteException(e);
        }
    }
}
