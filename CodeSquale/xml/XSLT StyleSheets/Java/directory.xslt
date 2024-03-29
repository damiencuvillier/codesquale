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
	xmlns:log4j="xalan://com.codesquale.xslt.LoggingExtension"
    extension-element-prefixes="redirect str log4j">
    
    
	<!-- XML Output Format -->
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	<xsl:variable name="LoggerName" value="TransformingCodeSqualeProcess" />
	
	<!--  File includes file scoped process -->
	<xsl:include href="file.xslt" />
	
	<!-- Global Variables -->
	<xsl:variable name="rootDir" select="/root/directory/@href" />
	
	<!--  Additionnal components -->
	<!--  String Extension Allows to make some usefull operation on strings -->
	<xalan:component prefix="str" elements="replace">
		<xalan:script lang="javaclass" src="xalan://com.codesquale.xslt.StringExtension" />
	</xalan:component>
	<!-- Log4J Extension allows to log message with log4j tool -->
	<xalan:component prefix="log4j" elements="log">
		<xalan:script lang="javaclass" src="xalan://com.codesquale.xslt.LoggingExtension" />
	</xalan:component>	
	
	
	<!-- Directory Process -->
	<xsl:template match="//directory">
		
		<log4j:log level="info" message="Analyse directory @href" loggerName="$LoggerName"/>

		<!-- Get Package Name -->
		<xsl:variable name="packageName">
			<xsl:choose>
				<xsl:when test="$rootDir = @href">
					<xsl:text></xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<!--
					 Get Package name
					  Erase root dir for package name
					  Replace \ to .
					 -->
				 	<xsl:value-of select="str:replace(str:replace(@href,concat($rootDir,'\'),''),'\','.')" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>


		<!-- Setting up the descriptionfile name -->
		<xsl:variable name="descriptionFileName">
			<xsl:choose>
				<xsl:when test="$packageName = ''">
					<!-- Particular case : the first (root) directory -->
					
					<!-- Replace %20 by white space (fix bug from ant) -->
					<xsl:value-of select="concat(@value,'.xml')" />
				</xsl:when>
				<xsl:otherwise>
					<!-- Replace %20 by white space (fix bug from ant) -->
					<xsl:value-of select="concat($packageName,'.xml')" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<!-- RedirectNode allows to write to separated files -->
		<redirect:write select="$descriptionFileName">
			<directory>
				<!-- XML Schema -->
				<xsl:attribute name="xsi:noNamespaceSchemaLocation">
					<xsl:text>DirectoryFile.xsd</xsl:text>
				</xsl:attribute>
				
				<!--  Directory name -->
				<xsl:attribute name="name">
					 <xsl:value-of select="@value" />
				</xsl:attribute>
				<!-- Absolute Path -->
				<xsl:attribute name="absolutePath">
					<xsl:value-of select="@href" />
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
					 		<!-- each dir name is concatened with package name
					 		So, all the description file are prefixed with packagename -->
					 		<xsl:variable name="completeName">
					 			<xsl:choose>
						 			<xsl:when test="$packageName != ''">
						 				<xsl:value-of select="concat($packageName,concat('.',@value))" />
						 			</xsl:when>
						 			<xsl:otherwise>
						 				<xsl:value-of select="@value" />
						 			</xsl:otherwise>
					 			</xsl:choose>
					 			
					 		</xsl:variable>
					 		<xsl:attribute name="descriptionFile">
					 			<xsl:value-of select="concat($completeName,'.xml')" />
					 		</xsl:attribute>
					 		<xsl:attribute name="name">
					 			<xsl:value-of select="@value" />
					 		</xsl:attribute>
					 		<xsl:attribute name="completeName">
					 			<xsl:value-of select="$completeName" />
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
							<xsl:attribute name="lastmodified">
								<xsl:value-of select="@modified" />
							</xsl:attribute>
							<xsl:attribute name="filesize">
								<xsl:value-of select="@size" />
							</xsl:attribute>
							<xsl:attribute name="toli">
								<xsl:value-of select="@toli" />
							</xsl:attribute>
							<xsl:attribute name="ploc">
								<xsl:value-of select="@ploc" />
							</xsl:attribute>
							<xsl:attribute name="blli">
								<xsl:value-of select="@blli" />
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