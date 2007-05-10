<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>
<!-- 
CodeSquale 
2007, April

Damien Cuvillier

XSLT StyleSheet

Antlr to CodeSquale XML transforming
-->
<!-- Transforming directory scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
	xmlns:xalan="http://xml.apache.org/xslt" 
	version="1.0"
	xmlns:redirect="http://xml.apache.org/xalan/redirect"
	xmlns:str="xalan://com.codesquale.xslt.StringExtension"
    extension-element-prefixes="redirect str">
    
    
	<!-- XML Output Format -->
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	
	<!--  File includes file scoped process -->
	<xsl:include href="file.xslt" />
	
	<!-- Global Variables -->
	
	
	<!--  Additionnal components -->
	<xalan:component prefix="str" elements="replace">
		<xalan:script lang="javaclass" src="xalan://com.codesquale.xslt.StringExtension" />
	</xalan:component>
	
	
	<!-- Directory Process -->
	<xsl:template match="//directory">
		<xsl:variable name="rootDir" select="/root/directory[@href]" />
		<xsl:message>Analyse directory <xsl:value-of select="@value" /></xsl:message>
		
		<!-- Create Package Name -->
		
		<xsl:message><xsl:value-of select="$rootDir" /></xsl:message>
		<xsl:variable name="packageName" select="str:replace(str:replace(@href,$rootDir,''),'\','.')" />


		<!-- RedirectNode allows to write to separated files -->
		<redirect:write select="concat(@value,'.xml')">
			<directory>
				<!-- XML Schema -->
				<xsl:attribute name="xsi:noNamespaceSchemaLocation">
					<xsl:text>DirectoryFile.xsd</xsl:text>
				</xsl:attribute>
				<!-- Absolute Path -->
				<xsl:attribute name="absolutePath">
					<xsl:value-of select="@href" />
				</xsl:attribute>
				<!--  Directory name -->
				<xsl:attribute name="name">
					 <xsl:value-of select="@value" />
				</xsl:attribute>
				<!--  Complete Name : equals to package Name -->
				<xsl:attribute name="completeName">
					 <xsl:value-of select="$packageName" />
				</xsl:attribute>
				<!-- DirectorySet contain all included directories
				Each directory is described in a separated file -->
				<directorySet>
					 <xsl:for-each select="directory">
					 	<dir>
					 		<xsl:attribute name="name">
					 			<xsl:value-of select="@value" />
					 		</xsl:attribute>
					 		<xsl:attribute name="descriptionFile">
					 			<xsl:value-of select="concat(@value,'.xml')" />
					 		</xsl:attribute>
					 		<xsl:attribute name="absolutePath">
								<xsl:value-of select="@href" />
							</xsl:attribute>
					 	</dir>
					 </xsl:for-each>
				</directorySet>
				<fileSet>
					<xsl:for-each select="file">
						<file>
							<xsl:attribute name="name">
								<xsl:value-of select="@value" />
							</xsl:attribute>
							<xsl:variable name="documentName" select="@href" />
							<xsl:apply-templates select="document($documentName)/*" />
						</file>
					</xsl:for-each>
				</fileSet>
				
			</directory>
			<xsl:apply-templates select="directory" />
		</redirect:write>
	</xsl:template>
	
	

</xsl:stylesheet>