
package main.java.com.comviva.flowone.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Clase Java para MaxReqTime complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MaxReqTime"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;int"&gt;
 *       &lt;attribute name="unit" default="minutes"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="minutes"/&gt;
 *             &lt;enumeration value="seconds"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaxReqTime", propOrder = {
    "value"
})
public class MaxReqTime {

    @XmlValue
    protected int value;
    @XmlAttribute(name = "unit")
    protected String unit;

    /**
     * Obtiene el valor de la propiedad value.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad unit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        if (unit == null) {
            return "minutes";
        } else {
            return unit;
        }
    }

    /**
     * Define el valor de la propiedad unit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}
