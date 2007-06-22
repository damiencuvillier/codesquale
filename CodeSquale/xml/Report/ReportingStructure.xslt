<?xml version="1.0" encoding="WINDOWS-1252"?>
<!-- 
CodeSquale 
2007, May

Damien Cuvillier

XSLT StyleSheet

Report Building
-->
<!-- Transforming datafiles to usefull files
 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance" xmlns:xalan="http://xml.apache.org/xslt" xmlns:str="xalan://com.codesquale.xslt.StringExtension" version="1.0" extension-element-prefixes="str">
	
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1"/>
	<xsl:strip-space elements="*"/>
	
	<!-- String Extension declaration 
	Associated with namespaces in file header -->
	<xalan:component prefix="str" elements="replace">
		<xalan:script lang="javaclass" src="xalan://com.codesquale.xslt.StringExtension"/>
	</xalan:component>
	
	<xsl:template match="/">
		<!--   Header -->
		<html>
		<head>
			<title>
				CodeSquale Directory Report
			</title>
			<link rel="stylesheet" type="text/css" href="../DataStructure.css" />
			<script type="text/javascript" src="../display.js">ho</script>
		</head>
		<body>
			<h1>Directory Report</h1>
			
			<!-- Metrics Box : display metrics results -->
			<div id="Cache"><span />
			</div>
			<div id="MetricsContent">
				<div id="CloseButton">
					<img src="../img/close.png" onClick="hideMetrics()" />
				</div>
				<iframe id="MetricsFrame" width="100%" height="100%" src="">
					test
				</iframe>
			</div>
			<xsl:apply-templates select="*"/>
		</body>
		</html>
	</xsl:template>
	
	<xsl:template match="project">
	<!--  Template only usefull for project.xml file -->
		<div id="navigBox">
			<img class="button"
				src="../img/graph01.gif" title="See Metrics for this directory" >
				<xsl:attribute name="onClick">
					<xsl:text>showMetrics('../metrics/project.html')</xsl:text>
				</xsl:attribute>
			</img>
		</div>
		<div id="headerBlock">
			Project Language :
			<xsl:value-of select="@language" />
			<br />
		</div>
		<br />
		<table cellspacing="0" cellpadding="0">
			<tr>
		        <th width="20%">Directory Name</th>
		        <th width="30%">Complete Name</th>
		        <th width="30%">Absolute Path</th>
		        <th width="15%">Metrics File</th>
		    </tr>
			<xsl:apply-templates select="*"/>
		</table>
	</xsl:template>
	
	
	<xsl:template match="directory">
	
		<div id="navigBox">
			<img class="button"
				src="../img/graph01.gif" title="See Metrics for this directory" >
				<xsl:variable name="linkValue">
					<!-- 
						Base dir exception
						When we are in base dir, completeName equals "". -->
						<xsl:choose>
							<xsl:when test="@completeName=''">
								<xsl:value-of select="@name" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="@completeName" />
							</xsl:otherwise>
						</xsl:choose>
						
				</xsl:variable>
				<xsl:attribute name="onClick">
					
					<xsl:text>showMetrics('../metrics/</xsl:text><xsl:value-of select="$linkValue" /><xsl:text>.html')</xsl:text>
				</xsl:attribute>
			</img>
			<a>
				<xsl:attribute name="href">
					<!-- Get the parent dir name -->
					<xsl:variable name="parentDir">
						<xsl:value-of select="str:replace(@completeName,concat('.',@name),'')" />
					</xsl:variable>
					<xsl:choose>
						<xsl:when test="$parentDir = @completeName">
							<!--  Si on est dans le premier répertoire  -->
							<xsl:text>project.html</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<!--  Dans tous les autres cas -->
							<xsl:value-of select="$parentDir"/><xsl:text>.html</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
					
				</xsl:attribute>
				<img src="../img/up.png" title="Go to ParentDir" />
			</a>
		</div>
		<div id="headerBlock">
			DirectoryName : 
			<xsl:value-of select="@name" />
			<br />
			CompleteName : 
			<xsl:value-of select="@completeName" />
			<br />
			AbsolutePath :
			<xsl:value-of select="@absolutePath" />
		</div>
		<xsl:apply-templates select="*"/>
	</xsl:template>
	<!-- Full content copying -->
<!-- 	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*"/>
		</xsl:copy>
	</xsl:template>-->
	<xsl:template match="directorySet">
		<h2>Directories List</h2>
		<table cellspacing="0" cellpadding="0">
			<tr>
		        <th width="20%">Directory Name</th>
		        <th width="30%">Complete Name</th>
		        <th width="30%">Absolute Path</th>
		        <th width="15%">Metrics File</th>
		    </tr>
			<xsl:apply-templates select="*"/>
		</table>
	</xsl:template>
	
	<xsl:template match="dir">
	
		
		<xsl:variable name="link">
			<xsl:choose>
				<xsl:when test="@name = 'xml'">
					<!--  Exception. Necesserary when package is called xml -->			
					<xsl:value-of select="concat(../../@completeName,'.xml.html')" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="str:replace(@descriptionFile,'.xml','.html')"/>
				</xsl:otherwise>
			</xsl:choose>
			
		</xsl:variable>
		<tr>
			<td>
				<a class="navigLink">
					<xsl:attribute name="href">
						<xsl:value-of select="$link"/>
					</xsl:attribute>
					<img src="../img/folder.png"/>
					<xsl:value-of select="@name"/>
				</a>
			</td>
			<td>
				<a class="navigLink">
					<xsl:attribute name="href">
						<xsl:value-of select="$link"/>
					</xsl:attribute>
					<xsl:value-of select="@completeName"/>
				</a>
			</td>
			<td>
				<a class="navigLink">
					<xsl:attribute name="href">
						<xsl:value-of select="$link"/>
					</xsl:attribute>
					<xsl:value-of select="@absolutePath"/>
					
				</a>
			</td>			
			<td align="center">
				<a>
					<xsl:attribute name="href">javascript:showMetrics('../Metrics/<xsl:value-of select="$link"/>')</xsl:attribute>
					<img src="../img/graph01.gif" title="See Associated Metrics" />
				</a>
			</td>	
			<xsl:apply-templates select="*"/>
		</tr>
	</xsl:template>
	
	<xsl:template match="fileSet">
		<h2>
			Files List
		</h2>
		<table class="file">
			<tr>
				<th></th>
				<th>
					FileName
				</th>
				<th>
					LastModified
				</th>
				<th>
					FileSize
				</th>
				<th>
					Total Lines
				</th>
				<th>
					Physical Lines
				</th>
				<th>
					Blank Lines
				</th>
			</tr>
			<xsl:apply-templates select="*" />
		</table>
	</xsl:template>
	
	<xsl:template match="file">
		<tr>
			<td>
				<img class="link" width="15px" src="../img/zoom.png">
					<xsl:attribute name="onClick">
						<xsl:text>switchDisplay('details</xsl:text><xsl:value-of select="@name" /><xsl:text>')</xsl:text>
					</xsl:attribute>
				</img>
			</td>
			<td>
				<img src="../img/file.png" title="file" />
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:value-of select="@lastmodified" />
			</td>
			<td>
				<xsl:value-of select="@filesize"/>kB
			</td>
			<td>
				<xsl:value-of select="@toli" />
			</td>
			<td>
				<xsl:value-of select="@ploc" />
			</td>
			<td>
				<xsl:value-of select="@blli" />
			</td>
		</tr>
		<tr>
			<td colspan="1"></td>
			<td colspan="6">
				<div class="details">
					<xsl:attribute name="id">
						<xsl:text>details</xsl:text><xsl:value-of select="@name" />
					</xsl:attribute>
					<div style="padding-left:30px">
						Imports<br />
						<xsl:apply-templates select="./importSet" />
						<br />
						Classe(s)<br />
						<xsl:apply-templates select="./classSet" />
					</div>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="importSet">
		<ul>
			<xsl:apply-templates select="*" />
		</ul>
	</xsl:template>
	<xsl:template match="import">
		<li class="import">
			<xsl:value-of select="@value" />
		</li>
	</xsl:template>
	
	<xsl:template match="classSet">
		
		<table class="classDetails">
			<tr>
				<th></th>
				<th>Name</th>
				<th>Package</th>
				<th>ParentClass</th>
				<th>Visibility</th>
			</tr>
			<xsl:apply-templates select="*" />
		</table>
	</xsl:template>
	<xsl:template match="class">
		<tr>
			<td>
				<img class="link" width="15px" src="../img/zoom.png">
					<xsl:attribute name="onClick">
						<xsl:text>switchDisplay('classdetails</xsl:text><xsl:value-of select="@name" /><xsl:text>')</xsl:text>
					</xsl:attribute>
				</img>
			</td>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:value-of select="@package"/>
			</td>
			<td>
				<xsl:value-of select="@parentClass" />
			</td>
			<td>
				<xsl:value-of select="@modifier" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div class="details">
				<xsl:attribute name="id">
					<xsl:text>classdetails</xsl:text><xsl:value-of select="@name" />
				</xsl:attribute>
				Interface(s)<br />
				<xsl:apply-templates select="./implementedInterfaceSet" />
				<br />
				Attribute(s)<br />
				<xsl:apply-templates select="./attributeSet" />
				Method(s)<br />
				<xsl:apply-templates select="./methodSet" />
				</div>
			</td>
		</tr>
	</xsl:template>
	<xsl:template match="implementedInterfaceSet">
		<ul>
			<xsl:apply-templates select="./interface" />
		</ul>
	</xsl:template>
		<xsl:template match="interface">
		<li>
			<xsl:apply-templates select="@name" />
		</li>
	</xsl:template>
	<xsl:template match="attributeSet">
		<table class="attributeDetails">
			<tr>
				<th>
					Name
				</th>
				<th>
					Type
				</th>
				<th>
					Visibility
				</th>
			</tr>
			<xsl:apply-templates select="./attribute" />
		</table>
	</xsl:template>
	<xsl:template match="attribute">
		<tr>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:value-of select="@type" />
			</td>
			<td>
				<xsl:value-of select="./modifierSet/visibility/@value" />
			</td>
		</tr>
	</xsl:template>
	<xsl:template match="methodSet">
	<table class="methodDetails">
			<tr>
				<th>
					Name
				</th>
				<th>
					Return Type
				</th>
				<th>
					Visibility
				</th>
			</tr>
			<xsl:apply-templates select="./method" />
		</table>
	</xsl:template>
	<xsl:template match="method">
		<tr>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:value-of select="@return" />
			</td>
			<td>
				<xsl:value-of select="@modifier" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
