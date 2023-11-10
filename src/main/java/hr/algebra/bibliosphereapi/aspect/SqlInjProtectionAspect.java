package hr.algebra.bibliosphereapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SqlInjProtectionAspect {

    @Pointcut("@annotation(SqlInjProtection)")
    public void annotatedMethod() {
    }

    @Before("annotatedMethod()")
    public void validateSqlStatements(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof String statement) {
                if (containsSqlStatements(statement)) {
                    throw new IllegalArgumentException("Invalid SQL statement: " + statement);
                }
            }
        }
    }

    private boolean containsSqlStatements(String statement) {
        String pattern = ".*\\b(DELETE|INSERT|UPDATE|DROP|ALTER)\\b.*";
        return statement.matches(pattern);
    }
}

