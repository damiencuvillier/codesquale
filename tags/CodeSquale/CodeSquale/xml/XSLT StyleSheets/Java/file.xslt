<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>
<!-- 
CodeSquale 
2007, April

Damien Cuvillier

XSLT StyleSheet

Antlr to CodeSquale XML transforming
-->
<!-- Transforming file scoped description from antlr to Codesquale Format -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	<xsl:include href="class.xslt" />
	<!--  Root launcher  -->
	<xsl:template match="antlr.CommonAST[@type=0]">
			<importSet>
				<xsl:apply-templates select="antlr.CommonAST[@type=30]"/>
			</importSet>
			<classSet>
				<xsl:apply-templates select="antlr.CommonAST[@type=14]" />
			</classSet>
	</xsl:template>
	
	<!--  ImportSet Process -->
	<xsl:template match="antlr.CommonAST[@type=30]">
		<import>
			<xsl:attribute name="value" >
				<!--  All childs which have type value = 68 ==> imports -->
				<xsl:for-each select="./*/*/*[@type=68]//@text">
					<!--  concatenation of the value and . -->
					<xsl:value-of select="concat(.,'.')" />
				</xsl:for-each>
				<xsl:value-of select="./*/*[@type=68]//@text" />
			</xsl:attribute>
		</import>
	</xsl:template>
	
	<!--  ClassSet Process -->
	<xsl:template match="antlr.CommonAST[@type=14]">
		<class>
			<xsl:attribute name="name">
				<xsl:value-of select="./*[@type=68]/@text" />
			</xsl:attribute>
			<xsl:attribute name="package" >
				<!--  All childs which have type value = 68 ==> imports -->
				<xsl:for-each select="../*[@type=16]//*[@type=68]//@text">
					<!--  concatenation of the value and . -->
					<xsl:value-of select="concat(.,'.')" />
				</xsl:for-each>
			</xsl:attribute>
			<xsl:attribute name="parentClass">
				<xsl:value-of select="./*[@type=18]//*[@type=68]/@text" />
			</xsl:attribute>

			<xsl:attribute name="modifier">
				<xsl:value-of select="./*[@type=5]/*[@type=89]/@text" />
			</xsl:attribute>
			
			<!-- ImplementedInterfaces -->
			<implementedInterfaceSet>
				<xsl:for-each select="./*[@type=19]//*[@type=68]/@text">
					<interface>
						<xsl:attribute name="name">
							<xsl:value-of select="." />
						</xsl:attribute>
					</interface>
				</xsl:for-each>
			</implementedInterfaceSet>
			<!-- Class Content -->
			<xsl:apply-templates select="./*[@type=6]" />
		</class>
	</xsl:template>

</xsl:stylesheet>
