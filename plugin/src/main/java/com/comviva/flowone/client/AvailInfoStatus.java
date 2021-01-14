
package main.java.com.comviva.flowone.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AvailInfoStatus.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="AvailInfoStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Starting"/&gt;
 *     &lt;enumeration value="Running"/&gt;
 *     &lt;enumeration value="Stopping"/&gt;
 *     &lt;enumeration value="Stopped"/&gt;
 *     &lt;enumeration value="Not reachable"/&gt;
 *     &lt;enumeration value="No response"/&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AvailInfoStatus")
@XmlEnum
public enum AvailInfoStatus {

    @XmlEnumValue("Starting")
    STARTING("Starting"),
    @XmlEnumValue("Running")
    RUNNING("Running"),
    @XmlEnumValue("Stopping")
    STOPPING("Stopping"),
    @XmlEnumValue("Stopped")
    STOPPED("Stopped"),
    @XmlEnumValue("Not reachable")
    NOT_REACHABLE("Not reachable"),
    @XmlEnumValue("No response")
    NO_RESPONSE("No response"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    AvailInfoStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AvailInfoStatus fromValue(String v) {
        for (AvailInfoStatus c: AvailInfoStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
