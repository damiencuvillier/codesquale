<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>

<!-- 
CodeSquale 
2007, April

Damien Cuvillier

XSLT StyleSheet

Antlr to CodeSquale XML transforming
-->
<!-- Transforming class scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	<!-- class root -->
	<xsl:template match="*[@type=6]">
		<attributeSet>
			<xsl:apply-templates select="antlr.CommonAST[@type=10]" />
		</attributeSet>
		<methodSet>
			<xsl:apply-templates select="antlr.CommonAST[@type=9]" />
		</methodSet>
	</xsl:template>
	
	<!--  Attributes -->
	<xsl:template match="antlr.CommonAST[@type=6]/antlr.CommonAST[@type=10]">
		<attribute>
			<xsl:attribute name="name">
				<xsl:value-of select="*/*[@type=68]/@text" />
			</xsl:attribute>
			<xsl:attribute name="type">
				<xsl:value-of select="./*[@type=13]/*/@text" />
			</xsl:attribute>
			<xsl:attribute name="modifier">
				<xsl:value-of select="./*[@type=5]/*/@text" />
			</xsl:attribute>

		</attribute>
	</xsl:template>
	<!-- Attributes -->
	
	<!--  Methods -->
	<xsl:template match="antlr.CommonAST[@type=6]/antlr.CommonAST[@type=9]">
		<method>
			<xsl:attribute name="name">
				<xsl:value-of select="./*[@type=68]/@text" />
			</xsl:attribute>
			<xsl:attribute name="returnType">
				<xsl:value-of select="./*[@type=13]/*[@type=68]/@text" />
			</xsl:attribute>
			<xsl:attribute name="modifier">
				<xsl:value-of select="./*[@type=5]/*[@type=68]/@text" />
			</xsl:attribute>
						
			
			<xsl:attribute name="instructionCount">
				<!--<xsl:for-each select="./*[@type=7]/*[@type=28]">
					<xsl:for-each select="descendant-or-self::*[@type=28]">
							$exprCount=$exprCount+1
					</xsl:for-each> 
				</xsl:for-each> -->
				<xsl:value-of select="count(./*[@type=7]/*[@type=28])" />
			</xsl:attribute>
			
		</method>
	</xsl:template>
	<!--  /Methods-->
</xsl:stylesheet>