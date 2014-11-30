<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">

<xsl:output method="text"/>

<xsl:template match="/staff/employee">
employee.<xsl:value-of select="position()"/>.name=<xsl:value-of select="name/text()"/>
employee.<xsl:value-of select="position()"/>.salay=<xsl:value-of select="salay/text()"/>
employee.<xsl:value-of select="position()"/>.hireday=<xsl:value-of select="hireday/@year/>-<xsl:value-of select="hireday/@month/>-<xsl:value-of select="hireday/@day/>
</xsl:template>

</xsl:stylesheet>



