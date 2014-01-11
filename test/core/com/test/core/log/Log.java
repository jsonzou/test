

package com.test.core.log;

import net.sourceforge.stripes.util.StringUtil;

import org.apache.commons.logging.LogFactory;


public class Log
{
    private org.apache.commons.logging.Log realLog;

    public org.apache.commons.logging.Log getRealLog()
    {
        return realLog;
    }

    public static Log getLogger()
    {
        return getInstance(getCallerClass());
    }

    public static Log getInstance(Class<?> clazz)
    {
        return new Log(LogFactory.getLog(clazz));
    }

    public static Log getInstance(String cate)
    {
        return new Log(LogFactory.getLog(cate));
    }

    public static void cleanup()
    {
        LogFactory.release(Thread.currentThread().getContextClassLoader());
    }

    private Log(org.apache.commons.logging.Log realLog)
    {
        this.realLog = realLog;
    }

    public final void fatal(Throwable throwable, Object... messageParts)
    {
        if (this.realLog.isFatalEnabled())
        {
            this.realLog.fatal(buildMsg(messageParts), throwable);
        }
    }

    public final void error(Throwable throwable, Object... messageParts)
    {
        if (this.realLog.isErrorEnabled())
        {
            this.realLog.error(buildMsg(messageParts), throwable);
        }
    }

    public final void warn(Throwable throwable, Object... messageParts)
    {
        if (this.realLog.isWarnEnabled())
        {
            this.realLog.warn(buildMsg(messageParts), throwable);
        }
    }

    public final void info(Throwable throwable, Object... messageParts)
    {
        if (this.realLog.isInfoEnabled())
        {
            this.realLog.info(buildMsg(messageParts), throwable);
        }
    }

    public final void debug(Throwable throwable, Object... messageParts)
    {
        if (this.realLog.isDebugEnabled())
        {
            this.realLog.debug(buildMsg(messageParts), throwable);
        }
    }

    public final void trace(Throwable throwable, Object... messageParts)
    {
        if (this.realLog.isTraceEnabled())
        {
            this.realLog.trace(buildMsg(messageParts), throwable);
        }
    }

    public final void fatal(Object... messageParts)
    {
        if (this.realLog.isFatalEnabled())
        {
            this.realLog.fatal(buildMsg(messageParts));
        }
    }

    public final void error(Object... messageParts)
    {
        if (this.realLog.isErrorEnabled())
        {
            String msg = buildMsg(messageParts);
            this.realLog.error(msg);
        }
    }

    public final void warn(Object... messageParts)
    {
        if (this.realLog.isWarnEnabled())
        {
            this.realLog.warn(buildMsg(messageParts));
        }
    }

    public final void info(Object... messageParts)
    {
        if (this.realLog.isInfoEnabled())
        {
            this.realLog.info(buildMsg(messageParts));
        }
    }

    public final void debug(Object... messageParts)
    {
        if (this.realLog.isDebugEnabled())
        {
            this.realLog.debug(buildMsg(messageParts));
        }
    }

    public final void trace(Object... messageParts)
    {
        if (this.realLog.isTraceEnabled())
        {
            this.realLog.trace(buildMsg(messageParts));
        }
    }

    private String buildMsg(Object... messageParts)
    {
        StringBuilder sb = new StringBuilder(invokeMsg());
        sb.append(StringUtil.combineParts(messageParts));
        String msg = sb.toString();
        return msg;
    }

    public static String getCallerClass()
    {
        String result = null;
        StackTraceElement stackTrace = findCaller();
        if (stackTrace != null)
        {
            result = stackTrace.getClassName();
        }
        return result;
    }

    public static String getCallerMethod()
    {
        String result = null;
        StackTraceElement stackTrace = findCaller();
        if (stackTrace != null)
        {
            result = stackTrace.getMethodName();
        }
        return result;
    }

    public static String invokeMsg()
    {
        StringBuilder sb = new StringBuilder("(");
        StackTraceElement stackTrace = findCaller();
        if (stackTrace != null)
        {
            sb.append(stackTrace.getMethodName());
            sb.append("():");
            sb.append(stackTrace.getLineNumber());
        }
        sb.append(")-");
        return sb.toString();
    }

    public static StackTraceElement findCaller()
    {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace)
        {
            if (!stackTraceElement.getClassName().equals(Log.class.getName()))
            {
                return stackTraceElement;
            }
        }
        return null;
    }

}
