# Introduction #

Logging is one of most common techniques to understand a process behaviour. By examining the log, a developer can check unexpected system behavior and correct it. A log also helps the developer see the interaction between different process of a system in order to detect exactly where the problem might be. In CodeSquale, log are used to trace process execution.

There's a lot of efficient logging system such as the standard java logging or log4j (wich is used in codesquale) but you always need logging statements everywhere needed in your code.

Generally, the logging system is implemented in the application core logic. Result is that logging statements are present in all modules of your project. The problem is when you need to change your logging strategy, it will be required to modify every module containing log expression.

To avoid crosscuting problem, we use aspect-oriented programming and AspectJ, the java extension for AOP concept. Using aspectj allow the development of a logging system independent of the application core and provides a modular logging mechanism.

In this page we won't deal the aspect-oriented programming concept, but we will introduce an aspectj overview and the usage in codesquale.

# The AspectJ Language #

## What is a jointpoint ##

A jointpoint is an indentifiable point in the execution of an application. It can be assimilated to method call or the assignment of an object member.
Here is a sample of jointpoint

```

// The jointpoint here is the method B and the amount assignment
public class A
{
   ...
   public void B(int x)
   {
        amount += x;
   }
}

```

## Pointcut ##

A pointcut is used to select one or more jointpoint and the associated context. For example you define a pointcut on a method call, the result is that you can intercept this method call and also capture the context (args, exceptions, ...)

```

// Here we define a pointcut that will catch every method call in the software
pointcut total() : execution(* *.*(..));

// These pointcuts will only catch the execute method in StatusTask
pointcut traceStatusTask (com.codesquale.ant.StatusTask s) : 
		target(s) 
		&& execution(public void  execute()) 
// equal to traceStatusTask
pointcut statusTask() : execution(public void com.codesquale.ant.StatusTask.execute(..));

```

## Advice ##

An advice is the code that will executed at a jointpoint defined by a pointcut. Advice can be executed in 3 ways :
  * before the joinpoint
  * after the jointpoint
  * around the jointpoint

The around advice allow developers to modify the execution of a jointpoint, he can modify the execution of the code, or replace totally.
For example you log a message before executing the code of a jointpoint.

```

// Display status message before call of execution method 
before(com.codesquale.ant.StatusTask s) : traceStatusTask(s){
	ParsingTraceAspect._logger.trace(s.getMessage());
}

```

## What is an aspect ##

Aspects are the AspectJ core units. Pointcuts, advices, and other AspectJ features are contained in an Aspect file. In addition to the AspectJ element, an aspect file can contains method, data, and other element. An aspect behave like a normal Java class.

Here is a complete sample of an aspect file

```

public aspect SampleAspect 
{
  pointcut statusTask() : execution(public void com.codesquale.ant.StatusTask.execute(..));

  before() : statusTask()
  {
     logger.info("Status ok");
  }

}

```

For more informations about AspectJ and OAP concept please visit :
http://www.eclipse.org/aspectj/
http://aosd.net/
http://www.dotnetguru.org/articles/dossiers/aop/aop.htm (french)


# Logging with AspectJ in Codesquale #

Every processes in CodeSquale (Parsing, Transformation, Metrics) can provides informations about status execution, possible errors,... to the Codesquale user.

Logging system is separated around business and technical concern. Codesquale need to log in a hand all the business sequences such as process status and in an other hand need to log for example exception raised during parsing, or metrics calculation errors.

To simplify logging system management, we identified 3 types of possible aspect:
  * Codesquale core process: each CodeSquale process class is combined with an aspect responsable of the logging mechanism during execution. It logs any critical step such as Parsing method call execution on a file...

  * Codesquale sequence process: logs the Codesquale execution sequence, provides only process starting and ending

  * Codesquale exception handling: to simplify exception logging we decided to extract exception catching in a specific aspect.


