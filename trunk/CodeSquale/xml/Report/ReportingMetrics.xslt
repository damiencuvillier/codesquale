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
	<xsl:template match="resultFile">
		<h2>Global Metrics for the entire Project</h2>
		<br />
		<xsl:apply-templates select="*" />
	</xsl:template>
	<xsl:template match="packageProperties">
		<!-- Header -->
		<h2>Directory Metrics</h2>
		PackageName : <xsl:value-of select="./analysisInformations/packageName" />
		<br />
		Absolute Path : <xsl:value-of select="./analysisInformations/packageAbsolutePath" />
		<br /><br />
		Number of files : 
		<xsl:value-of select="./analysisInformations/numberOfFiles" />
		<br />
		Global Size : 
		<xsl:value-of select="./analysisInformations/weightOfFiles" />kB
		<br />
		Lines of code : 
		<ul>
			<li>
				Total Lines : 
				<xsl:value-of select="./sizeMetrics/numberOfLines/numberOfTotalLines" />
			</li>
			<li>
				Physical Lines : 
				<xsl:value-of select="./sizeMetrics/numberOfLines/numberOfPhysicalLines" />
			</li>
			<li>
				Blank Lines : 
				<xsl:value-of select="./sizeMetrics/numberOfLines/numberOfBlankLines" />
			</li>
		</ul>
		<br />
		Number of classes : 
		<xsl:value-of select="./sizeMetrics/numberOfClasses/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./sizeMetrics/numberOfClasses/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./sizeMetrics/numberOfClasses/others" />
			</li>
		</ul>
		<br />
		Number of methods : 
		<xsl:value-of select="./sizeMetrics/numberOfMethods/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./sizeMetrics/numberOfMethods/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./sizeMetrics/numberOfMethods/others" />
			</li>
		</ul>
		<br />
		Number of attributes : 
		<xsl:value-of select="./sizeMetrics/numberOfAttributes/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./sizeMetrics/numberOfAttributes/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./sizeMetrics/numberOfAttributes/others" />
			</li>
		</ul>
		<br />
		Number of attributes : 
		<xsl:value-of select="./sizeMetrics/numberOfImplementedInterfaces" />
		<br />
		<br />
		Average number of methods per class : 
		<xsl:value-of select="./ratioMetrics/averageMethodNumberPerClass/all" />
		<ul>
			<li>
				Public : 
				<xsl:value-of select="./ratioMetrics/averageMethodNumberPerClass/public" />
			</li>
			<li>
				Others : 
				<xsl:value-of select="./ratioMetrics/averageMethodNumberPerClass/others" />
			</li>
		</ul>
		<br />
		<!-- Average number of attributes per class : 
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
		<br />-->
	</xsl:template>
	<!--  // end of project.xml file -->
	
	
	
	<xsl:template match="fileSetAnalysis">
		<h2>Files List</h2>
		<div class="resultContent">
			
			<xsl:apply-templates select="*" />
		</div>
	</xsl:template>

	<xsl:template match="fileAnalysis">
		<h3>File <xsl:value-of select="./fileProperties/fileName"/></h3>
		<h4>Properties</h4>
		<ul>
			<li>
				Size : <xsl:value-of select="./fileProperties/fileSize" />kB
			</li>
			<li>
				Last Modified : 
				<xsl:value-of select="./fileProperties/fileLastModification" />
			</li>
		</ul>
		<h4>Global Metrics - Lines Count</h4>
		<ul>
			<li>
				Total Lines : <xsl:value-of select="./sizeMetrics/numberOfLines/numberOfTotalLines" />
			</li>
			<li>
				Physical lines : <xsl:value-of select="./sizeMetrics/numberOfLines/numberOfPhysicalLines" />
			</li>
			<li>
				Blank Lines : <xsl:value-of select="./sizeMetrics/numberOfLines/numberOfBlankLines" />
			</li>
		</ul>
		<h4>Classes Count</h4>
		<ul>
			<li>
				All : <xsl:value-of select="./sizeMetrics/numberOfClasses/all" />
			</li>
			<li>
				Public : <xsl:value-of select="./sizeMetrics/numberOfClasses/public" />
			</li>
			<li>
				Others : <xsl:value-of select="./sizeMetrics/numberOfClasses/others" />
			</li>
		</ul>
		<h4>Methods Count</h4>
		<ul>
			<li>
				All : <xsl:value-of select="./sizeMetrics/numberOfMethods/all" />
			</li>
			<li>
				Public : <xsl:value-of select="./sizeMetrics/numberOfMethods/public" />
			</li>
			<li>
				Others : <xsl:value-of select="./sizeMetrics/numberOfMethods/others" />
			</li>
		</ul>
		<h4>Attributes Count</h4>
		<ul>
			<li>
				All : <xsl:value-of select="./sizeMetrics/numberOfAttributes/all" />
			</li>
			<li>
				Public : <xsl:value-of select="./sizeMetrics/numberOfAttributes/public" />
			</li>
			<li>
				Others : <xsl:value-of select="./sizeMetrics/numberOfAttributes/others" />
			</li>
		</ul>
		<h4>Implemented Interfaces Count </h4>
			<xsl:value-of select="./sizeMetrics/numberOfImplementedInterfaces" />
		<xsl:apply-templates select="./classSetAnalysis" />		
	</xsl:template>
	<xsl:template match="classSetAnalysis">
		<h4>Classes</h4>
		<xsl:apply-templates select="*" />
	</xsl:template>
	<xsl:template match="classAnalysis">
		<div class="resultContent">
			<h5>Class <xsl:value-of select="./classProperties/className" /></h5>
			<h6>Methods</h6>
			<ul>
				<li>
					Public 
					<xsl:value-of select="./sizeMetrics/numberOfMethods/all" />
				</li>
				<li>
					Others 
					<xsl:value-of select="./sizeMetrics/numberOfMethods/others" />
				</li>
				<li>
					Public 
					<xsl:value-of select="./sizeMetrics/numberOfMethods/all" />
				</li>
			</ul>
			<h6>Attributes</h6>
			<ul>
				<li>
					Public 
					<xsl:value-of select="./sizeMetrics/numberOfAttributes/public" />
				</li>
				<li>
					Others 
					<xsl:value-of select="./sizeMetrics/numberOfAttributes/others" />
				</li>
				<li>
					Public 
					<xsl:value-of select="./sizeMetrics/numberOfAttributes/all" />
				</li>
			</ul>
		</div>
	</xsl:template>
</xsl:stylesheet>
