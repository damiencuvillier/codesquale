## Some words about the project leading method ##
The project is organized by using short time iteration. Thus, the development team will be able to provide often some binaries. Each iteration will be focused on particular objectives.

For example, in the begin of the project development, iteration will be focused on the development of the main functionnality of CodeSquale. Then, we will concentrate our effort  on topics like performance or architecture and so on.

We don't have planned all our tasks until the end and the aim of short iteration is to provide the developpment team a flexible progression, focused on what's important for us.

Our project organisation is based on the RUP (Rational Unified Process). By getting a lot of _quick wins_, the project evolution will be easily visible.

## MAIN STEPS ##
  * 2007-03-30 : First Binary ==> Count numbers of classes, files and lines

## COMING EVOLUTIONS ##

  * **Revision of the software architecture**

CodeSquale will be more distinctly divided in three parts :
  * **CS Parsing System :  analyzes the source code and produces XML representation of it.**
  * **XSLT Data Tranformation Processing : transforms XML data**
  * **Metrics calculation system : calcultes metrics from the file stored data**

  * **Plenty of counters and metrics**

The list of counters and metrics on the project wiki page is only the beginning of our software metrics inventory. We can provide you more and more metrics... coming soon.



## RELEASE NOTES ##
### V 0.1 [2007-03-30] ###
> ### What's new ###
First release of CodeSquale. It is an alpha version but produces few results.

> ### Functionnalities ###
  * Analyses a set of java source files
  * Counts some indicators and calculate one metric
  * Display process of analysis on the console
  * Export results into text files

> ### What's calculated ? ###
Counters :
  * Lines
  * Files
  * Classes
  * Methods
  * Interfaces
Metrics :
  * Average number of method by class

> ### Download, install and uninstall ###
This first release is not available for the moment because the CodeSquale team estimates that it was too little for the moment. But don't desperate, a new release is coming soon.

> ### Known issues ###
  * The second parameter (result output file) must be a valid file name.