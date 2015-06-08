# What is JPF ? #
JPF provides a runtime engine that dynamically discovers and loads "plug-ins". A plug-in is a structured component that describes itself to JPF using a "manifest". JPF maintains a registry of available plug-ins and the functions they provide (via extension points and extensions).

One major goal of JPF is that the application (and its end-user) should not pay any memory or performance penalty for plug-ins that are installed, but not used. Plug-ins are added to the registry at application start-up or while the application is running but they are not loaded until they are called.

# Modularity and extensivity #


## Modules and Application Modularity ##

Simply speaking, modularization is splitting an application into several smaller parts. The JPF framework allows this by using the concept of plug-ins. This means you can think
of plug-ins as a collection of classes and resources managed by special ClassLoader that makes them available to all dependent plug-ins transparently.

Lets say a plug-in PluginA introduces a class ClassA (this class is included in a plug-in directory hierarchy described by a JPF plug-in manifest). Now you are developing another plug-in, PluginB, and add another class, ClassB, to this plug-in. You want to reference ClassA in your ClassB code so you need to declare a plug-in dependency. You can do this by making an entry in the JPF manifest of the plug-in PluginB that says "PluginB depends on PluginA". This is done in the prerequisites/imports section of the JPF manifest. JPF handles finding and loading ClassA when it is first called. The magic lies in the ClassLoaders created by JPF. The extend the classpath of PluginB so that it includes the classpath of PluginA. So the developer doesn't have to worry about finding classes and can use the basic code that follows in ClassB :

ClassA clsA = new ClassA();

No further work is necessary to make ClassA visible for ClassB code, only simple JPF manifest declarations.

## Extensions and Application Extensibility ##

Simply speaking, application extensibility is adding on to already existing functionality. JPF supports this with special manifest declarations. In JPF extensibility is based on the concept of extension points and extensions. An extenstion point is an opening that may be added to by later code. An extension is code that adds onto an existing extension point. Typically extension points are declared in a plug-in manifest and supported with Java code There is no special dedicated API for such code in JPF as it can be anything! For examples of extension points see the JPF demo application.

When designing applications based on JPF it is better to think in terms of extension points and extensions rather than plug-ins. In general, it doesn't matter where (in what plug-in) the actual code and/or resources are placed. It is much more important to define where an application can be extended, and design and develop extension points to support this extensibility.


# Modularity in codesquale #

JPF provides codesquale the way to be modular and extensive. Codesquale is divided into few main components :

**Component 1 : ParsingSystem**

The component 1 is responsible to analyse files and produce logical representation of the source code (AST : abstract syntaxic tree).

**Component 2 : DataCollector**

This component is in charge to collect general volumetric data by browsing the AST.

**Component 3 : MetricsCalculator**

This component is able from the DataCollector output to calculate the metrics provided by codesquale.

## Extensivity in codesquale ##

**Extensivity 1 : Support of multiple language**

By externalising the parsing system, codesquale is able to load at runtime the approriate parser from the user desired language type.

**Extensivity 2 : Support of new metrics**

Codesquale supports the add of new metrics.