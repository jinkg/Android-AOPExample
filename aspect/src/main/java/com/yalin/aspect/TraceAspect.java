/*
 * Copyright 2017 Yalin Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yalin.aspect;

import com.yalin.aspect.internal.DebugLog;
import com.yalin.aspect.internal.StopWatch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author jinyalin
 * @since 2017/3/31.
 */
@Aspect
public class TraceAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.yalin.aspect.annotation.DebugTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.yalin.aspect.annotation.DebugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedWithDebugTrace() {
    }

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedWithDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        DebugLog.log(className, buildLogMessage(className, methodName, stopWatch.getTotalTimeMillis()));

        return result;
    }

    private static String buildLogMessage(String className, String methodName, long methodDuration) {
        return className + "." + methodName + " --> " + "[" + methodDuration + "ms" + "]";
    }
}
