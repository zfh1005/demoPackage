<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
<xsl:output method="html"/>
<xsl:template match="/staff">
<table border="1">
<xsl:apply-templates/>
</table>
</xsl:template>

<xsl:template match="/staff/employee">
<tr>
<xsl:apply-templates/>
</tr>
</xsl:template>

<xsl:template match="/staff/employee/name">
<td>
<xsl:apply-templates/>
</td>
</xsl:template>

<xsl:template match="/staff/employee/salay">
<td>
<xsl:apply-templates/>
</td>
</xsl:template>

<xsl:template match="/staff/employee/hireday">
<td>
<xsl:value-of select="@year"/>-<xsl:value-of select="@month"/>"/>-<xsl:value-of select="@day"/>
</td>
</xsl:template>

</xsl:stylesheet>
