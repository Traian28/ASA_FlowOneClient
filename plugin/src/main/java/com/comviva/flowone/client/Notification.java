
package main.java.com.comviva.flowone.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Notification is an intermediate message object that InstantLink may send during request processing to response service. Notifications may be produced by Business Service Tool, Network Element Interface or InstantLink internally.
 * 
 * <p>Clase Java para Notification complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Notification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}OrderNo"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}RequestId"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}Level"/&gt;
 *         &lt;element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Notification", propOrder = {
    "orderNo",
    "requestId",
    "level",
    "timeStamp",
    "messageId",
    "message"
})
public class Notification {

    @XmlElement(name = "OrderNo", required = true)
    protected String orderNo;
    @XmlElement(name = "RequestId")
    protected long requestId;
    @XmlElement(name = "Level")
    protected int level;
    @XmlElement(name = "TimeStamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElement(name = "Message", required = true)
    protected String message;

    /**
     * The OSS/BSS identifier of the request
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Define el valor de la propiedad orderNo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNo(String value) {
        this.orderNo = value;
    }

    /**
     * Unique request Id given by InstantLink
     * 
     */
    public long getRequestId() {
        return requestId;
    }

    /**
     * Define el valor de la propiedad requestId.
     * 
     */
    public void setRequestId(long value) {
        this.requestId = value;
    }

    /**
     * The notification level of this notification. 3 = BST notification, 4 = NEI phase message, 5 and above = System message
     * 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Define el valor de la propiedad level.
     * 
     */
    public void setLevel(int value) {
        this.level = value;
    }

    /**
     * Obtiene el valor de la propiedad timeStamp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Define el valor de la propiedad timeStamp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Obtiene el valor de la propiedad messageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Define el valor de la propiedad messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Obtiene el valor de la propiedad message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define el valor de la propiedad message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
