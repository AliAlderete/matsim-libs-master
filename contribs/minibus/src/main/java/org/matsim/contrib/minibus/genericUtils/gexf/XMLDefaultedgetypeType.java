//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.19 at 03:18:45 PM MESZ 
//


package org.matsim.contrib.minibus.genericUtils.gexf;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for defaultedgetype-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="defaultedgetype-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="directed"/>
 *     &lt;enumeration value="undirected"/>
 *     &lt;enumeration value="mutual"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "defaultedgetype-type")
@XmlEnum
public enum XMLDefaultedgetypeType {

    @XmlEnumValue("directed")
    DIRECTED("directed"),
    @XmlEnumValue("undirected")
    UNDIRECTED("undirected"),
    @XmlEnumValue("mutual")
    MUTUAL("mutual");
    private final String value;

    XMLDefaultedgetypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static XMLDefaultedgetypeType fromValue(String v) {
        for (XMLDefaultedgetypeType c: XMLDefaultedgetypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
