<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- 
CodeSquale 
2007, May

Damien Cuvillier

XSLT StyleSheet

Report Building
Metrics Part
-->
<!-- Transforming datafiles to usefull files
 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.0">
	
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1"/>
	<xsl:strip-space elements="*"/>
	
	
	
	<xsl:template match="/">
		<!--  Header -->		
		<html>
			<head>
				<title>CodeSquale Metrics Report</title>
				<link rel="stylesheet" type="text/css" href="../metrics.css" />
			</head>
			<body>
				<h1>Metrics Report</h1>
				<xsl:apply-templates select="node()"/>
			</body>
		</html>
	</xsl:template>
	
	<!--  Project.xml file -->
	<xsl:template match="projectResults">
		<h2>Global Metrics for the entire Project</h2>
		<br />
		<xsl:apply-templates select="*" />
	</xsl:template>
	<xsl:template match="projectGlobalMetrics">
		Number of files : 
		<xsl:value-of select="./files" />
		<br />
		Global Size : 
		<xsl:value-of select="./size" /><xsl:value-of select="./size/@unit" />
		<br />
		Lines of code : 
		<xsl:value-of select="./linesOfCode/all" />
		<ul>
			<li>
				Total Lines : 
				<xsl:value-of select="./linesOfCode/toli" />
			</li>
			<li>
				Physical Lines : 
				<xsl:value-of select="./linesOfCode/ploc" />
			</li>
			<li>
				Blank Lines : 
				<xsl:value-of select="./linesOfCode/blli" />
			</li>
		</ul>
		<br />
		Number of classes : 
		<xsl:value-of select="./classes/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./classes/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./classes/others" />
			</li>
		</ul>
		<br />
		Number of methods : 
		<xsl:value-of select="./methods/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./methods/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./methods/others" />
			</li>
		</ul>
		<br />
		Number of attributes : 
		<xsl:value-of select="./attributes/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./attributes/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./attributes/others" />
			</li>
		</ul>
		<br />
	</xsl:template>
	
	<xsl:template match="projectGlobalRatios">
		Average number of methods per class : 
		<xsl:value-of select="./averageNumberMethodsPerClass/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./averageNumberMethodsPerClass/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./averageNumberMethodsPerClass/others" />
			</li>
		</ul>
		<br />
		Average number of attributes per class : 
		<xsl:value-of select="./averageNumberAttributesPerClass/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./averageNumberAttributesPerClass/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./averageNumberAttributesPerClass/others" />
			</li>
		</ul>
		<br />
	</xsl:template>
	<!--  // end of project.xml file -->
	
	<xsl:template match="directoryResults">
		<!-- Header -->
		<h2>Directory Metrics</h2>
		PackageName : <xsl:value-of select="@packageName" />
		<br />
		Absolute Path : <xsl:value-of select="@absolutePath" />
		<xsl:apply-templates select="*" />
		
		
	</xsl:template>
	<xsl:template match="packageAnalysis">
		<h2>Package analysis</h2>
		<div class="resultContent">
			<xsl:apply-templates select="*"/>
		</div>
	</xsl:template>
	<xsl:template match="packageGlobalMetrics">
		<h3>Files</h3>
		Count : <xsl:value-of select="./files" />
		<h3>Lines of code</h3>
		<ul>
			<li>
				Total : <xsl:value-of select="./linesOfCode/toli" />
			</li>
			<li>
				Physical Lines of code (PLOC) : <xsl:value-of select="./linesOfCode/ploc" />
			</li>
			<li>
				Blank Lines (BLLI) : <xsl:value-of select="./linesOfCode/blli" />
			</li>
		</ul>
		<h3>
			Classes
		</h3>
		<ul>
			<li>
				Public
				<xsl:value-of select="./classes/public" />
			</li>
			<li>
				Others
				<xsl:value-of select="./classes/others" />
			</li>
			<li>
				All
				<xsl:value-of select="./classes/all" />
			</li>
		</ul>
		<h3>
			Methods
		</h3>
		<ul>
			<li>
				Public
				<xsl:value-of select="./methods/public" />
			</li>
			<li>
				Others
				<xsl:value-of select="./methods/others" />
			</li>
			<li>
				All
				<xsl:value-of select="./methods/all" />
			</li>
		</ul>
		<h3>
			Methods
		</h3>
		<ul>
			<li>
				Public
				<xsl:value-of select="./attributes/public" />
			</li>
			<li>
				Others
				<xsl:value-of select="./attributes/others" />
			</li>
			<li>
				All
				<xsl:value-of select="./attributes/all" />
			</li>
		</ul>
	</xsl:template>
	<xsl:template match="packageRatios">
	<!-- Package Ratios -->
		<h2>
			Ratios
		</h2>
		<h3>
			Average Method Number Per Class
		</h3>
		<ul>
			<li>
				Public
				<xsl:value-of select="./averageMethodNumberPerClass/public" />
			</li>
			<li>
				Others
				<xsl:value-of select="./averageMethodNumberPerClass/others" />
			</li>
			<li>
				All
				<xsl:value-of select="./averageMethodNumberPerClass/all" />
			</li>
		</ul>
		<h3>
			Average Attribute Number Per Class
		</h3>
		<ul>
			<li>
				Public
				<xsl:value-of select="./averageAttributeNumberPerClass/public" />
			</li>
			<li>
				Others
				<xsl:value-of select="./averageAttributeNumberPerClass/others" />
			</li>
			<li>
				All
				<xsl:value-of select="./averageAttributeNumberPerClass/all" />
			</li>
		</ul>
	</xsl:template>
	
	
	<xsl:template match="fileSet">
		<h2>Files List</h2>
		<div class="resultContent">
			
			<xsl:apply-templates select="*" />
		</div>
	</xsl:template>

	<xsl:template match="file">
		<h3>File <xsl:value-of select="@name"/></h3>
		<h4>Properties</h4>
		<ul>
			<li>
				Size : <xsl:value-of select="./fileProperties/size" /> <xsl:value-of select="./fileProperties/size/@unit" />
			</li>
			<li>
				Last Modified : 
				<xsl:value-of select="./fileProperties/lastmodified" />
			</li>
		</ul>
		<h4>Global Metrics - Lines Count</h4>
		<ul>
			<li>
				Total Lines : <xsl:value-of select="./globalMetrics/lineOfCode/toli" />
			</li>
			<li>
				Physical lines : <xsl:value-of select="./globalMetrics/lineOfCode/ploc" />
			</li>
			<li>
				Blank Lines : <xsl:value-of select="./globalMetrics/lineOfCode/blli" />
			</li>
		</ul>

		<xsl:apply-templates select="./classSet" />		
	</xsl:template>
	<xsl:template match="classSet">
		<h4>Classes</h4>
		<xsl:apply-templates select="*" />
	</xsl:template>
	<xsl:template match="class">
		<div class="resultContent">
			<h5>Class <xsl:value-of select="@name" /></h5>
			<h6>Methods</h6>
			<ul>
				<li>
					Public 
					<xsl:value-of select="./methodCount/public" />
				</li>
				<li>
					Others 
					<xsl:value-of select="./methodCount/others" />
				</li>
				<li>
					Public 
					<xsl:value-of select="./methodCount/all" />
				</li>
			</ul>
			<h6>Attributes</h6>
			<ul>
				<li>
					Public 
					<xsl:value-of select="./attributeCount/public" />
				</li>
				<li>
					Others 
					<xsl:value-of select="./attributeCount/others" />
				</li>
				<li>
					Public 
					<xsl:value-of select="./attributeCount/all" />
				</li>
			</ul>
		</div>
	</xsl:template>
</xsl:stylesheet>
