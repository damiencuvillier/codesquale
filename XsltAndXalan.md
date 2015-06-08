# Introduction #

Add your content here.


# Why using XSLT #
XSLT comes from w3c recommandation. It allows to transform a xml file to anything else. In our case, we need to transform antlr xml tree to a owned format which can be easily queried.

We could do it with traditionnal java xml parsing with SAX, DOM or XERCES. But in this case, we have to build a technic base which costs time and efforts and finally give no extra value.

Indeed, we want to focus on our goal : give metrics. We think it is not usefull to use technic only for technic.

# How to use XSLT technology #
XSLT files are called stylesheets. There are in fact simple xml files.
A basic XLST File is


<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>

<!-- Transforming directory scoped description -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"

> xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"

> version="1.0">

> <!-- XML Output Format -->

> 

&lt;xsl:output method="xml" indent="yes" encoding="ISO-8859-1" /&gt;



> 

&lt;xsl:strip-space elements="\*" /&gt;



> <!-- Templates Here-->



Unknown end tag for &lt;/stylesheet&gt;





As all xml technologies, XSLT is just a specification. It is hard to find a free standard implementation. We found Saxon. But Saxon developers give an ambiguous commercial politic.  After that, we found Xalan, which is the Apache Implementation.
Even if some aspects are not really standard, it is totally free and finally well featured.


You can find some good information about Xalan & XSLT

[here](http://xml.apache.org/xalan-j/overview.html).


**External links**
  * [How to extend Xalan with Java](http://groups.google.com/group/codesquale-devel/t/2fe26e8d7cea0851)
  * [Redirect xalan feature](http://groups.google.com/group/codesquale-devel/t/a0c849bf37cb9ca9) : give multiple destination documents


# How to "lauch" a xslt stylesheet #

As XSLT module is only one part of the full featured CodeSquale, we use Ant to launch Xalan Processor. The launch is contained in AntScript.xml.

Please Refer to Ant Wiki Page for more information about our use of Ant.

The XSLT ant Task follows this syntax


<xslt basedir="." processor="trax"

> in="

&lt;inputDir&gt;

"

> out="

&lt;outputDir&gt;

"

> extension="xml"

> description="Directory files parsing"

> style="<xslt stylesheet path>"

> >

> 

&lt;factory name="org.apache.xalan.processor.TransformerFactoryImpl" /&gt;





Unknown end tag for &lt;/xslt&gt;




The difficult point is that we must included all necessary libraries in Ant Home. It seems really easy but it means that it has to be done in Development Platform, Testing PlatForm, Binary software...
It causes lots of bugs..
Still looking for improvements...