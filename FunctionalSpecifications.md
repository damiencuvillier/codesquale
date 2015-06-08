# Introduction #

Before starting the project, it is necessary to specify the goals of the project. This document is the reference one for the entire project.Its role is to be exhaustive.

If you want to use this project, the document will help you knowing if CodeSquale fits to your needs.

_Note : After reading the RoadMap page, you will understand easily the project lifecycle._

# Context #
During the last years, offshoring and externalization has been more and more used to lead IT project. It has become necessary to control the quality of the product delivered by the suppliers. Especially in software development, the **code source quality** is **vital** for the **maintenability**, **reliability** and **scability** of an application. And these aspects are very important because they are indicator of the **software development cost**.

In this particular context has appeared the **source code audit**. There are several kind of audit :
  * security audit
  * architecture audit
  * syntax audit
  * dependency audit

`CodeSquale` is placed on the last one. The project is borned from a lack of fitted solution. Indeed, most of the actual solutions are dynamic, which means that we need to compile the source code to test it. This fact


# Features #
`CodeSquale` can analyze a set of source code files, and build from them a logical tree, representating the software architecture. Based on this tree, CodeSquale is able to provide plenty of documented software metrics, which represent the global coding quality.

`CodeSquale` must be very modular. Indeed, it aims to test a lot of softwares, with different programming language, different architecture, etc... The technical specifications must consider this point.

## How to use CodeSquale ##
Here is the future way of using `CodeSquale`.
Regroup all the source code in one folder. You don't need to have all the project code and all librairies. You just need to have the source files (i.e. files with .java extension).

### 1' ###
The first step aims to **collect all the data we can about the project struture**. All theses informations will be used in the next step to calculate metrics. To do that, you will use a shell command : `CS-collect.bat <path>` where 

&lt;path&gt;

 is the path where your source code is located. This command provide an XML file which contains all the data.

This step is composed by three parts :
  * Lexing
  * Parsing
  * Building a representative tree

### 2' ###
The second step aims to **sort the collected data to calculate all the metrics**. We will give a complete documentation for each metric. Use the following syntax to use this tool : `CS-analyse.bat <file>`. 

&lt;file&gt;

 is the XML file given by the previous command.

Most of the others open source projects we found, are not full documented. Consequently, it is difficult to analyse some metric and to give advices to solve the identified issue.

The document SoftwareMetricsExplanation will be our reference. We aim to provide all the metrics which are included in this document.

Besides, the architecture of this feature must be very modular. Thus, it will work by using a plugin system. Each metric will be a plugin.

![http://codesquale.googlepages.com/MoulinettesProcessSADT.png](http://codesquale.googlepages.com/MoulinettesProcessSADT.png)
SADT Diagram