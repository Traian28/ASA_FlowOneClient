
package main.java.com.comviva.flowone.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * InstantLinkAvailabilityInfo contains information about the current status of InstantLink
 * 
 * <p>Clase Java para InstantLinkAvailabilityInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InstantLinkAvailabilityInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InstantLinkInstance" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RunningStatus" type="{http://soa.comptel.com/2011/02/instantlink}AvailInfoStatus"/&gt;
 *         &lt;element name="Uptime" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstantLinkAvailabilityInfo", propOrder = {
    "instantLinkInstance",
    "runningStatus",
    "uptime"
})
public class InstantLinkAvailabilityInfo {

    @XmlElement(name = "InstantLinkInstance", required = true)
    protected String instantLinkInstance;
    @XmlElement(name = "RunningStatus", required = true)
    @XmlSchemaType(name = "string")
    protected AvailInfoStatus runningStatus;
    @XmlElement(name = "Uptime")
    protected long uptime;

    /**
     * Obtiene el valor de la propiedad instantLinkInstance.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstantLinkInstance() {
        return instantLinkInstance;
    }

    /**
     * Define el valor de la propiedad instantLinkInstance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstantLinkInstance(String value) {
        this.instantLinkInstance = value;
    }

    /**
     * Obtiene el valor de la propiedad runningStatus.
     * 
     * @return
     *     possible object is
     *     {@link AvailInfoStatus }
     *     
     */
    public AvailInfoStatus getRunningStatus() {
        return runningStatus;
    }

    /**
     * Define el valor de la propiedad runningStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailInfoStatus }
     *     
     */
    public void setRunningStatus(AvailInfoStatus value) {
        this.runningStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad uptime.
     * 
     */
    public long getUptime() {
        return uptime;
    }

    /**
     * Define el valor de la propiedad uptime.
     * 
     */
    public void setUptime(long value) {
        this.uptime = value;
    }

}
