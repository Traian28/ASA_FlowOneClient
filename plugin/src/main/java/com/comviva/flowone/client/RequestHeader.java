
package main.java.com.comviva.flowone.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * RequestHeader contains parameters to control the request processing
 * 
 * <p>Clase Java para RequestHeader complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RequestHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}NeType"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}OrderNo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}Priority" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}ReqUser" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}ReqStartTime" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}MaxReqTime" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}RetransmissionFlag" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}RetransmissionKey" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}BypassLock" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}AddNotificationsToResponse" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}NotificationMsgLevel" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeader", propOrder = {
    "neType",
    "orderNo",
    "priority",
    "reqUser",
    "reqStartTime",
    "maxReqTime",
    "retransmissionFlag",
    "retransmissionKey",
    "bypassLock",
    "addNotificationsToResponse",
    "notificationMsgLevel"
})
public class RequestHeader {

    @XmlElement(name = "NeType", required = true)
    protected String neType;
    @XmlElement(name = "OrderNo")
    protected String orderNo;
    @XmlElement(name = "Priority")
    protected Integer priority;
    @XmlElement(name = "ReqUser")
    protected String reqUser;
    @XmlElement(name = "ReqStartTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reqStartTime;
    @XmlElement(name = "MaxReqTime")
    protected MaxReqTime maxReqTime;
    @XmlElement(name = "RetransmissionFlag")
    protected Boolean retransmissionFlag;
    @XmlElement(name = "RetransmissionKey")
    protected String retransmissionKey;
    @XmlElement(name = "BypassLock")
    protected Boolean bypassLock;
    @XmlElement(name = "AddNotificationsToResponse")
    protected Boolean addNotificationsToResponse;
    @XmlElement(name = "NotificationMsgLevel")
    protected Integer notificationMsgLevel;

    /**
     * Network element type
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeType() {
        return neType;
    }

    /**
     * Define el valor de la propiedad neType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeType(String value) {
        this.neType = value;
    }

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
     * Priority indicator: 1 (high) - 10 (low)
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Define el valor de la propiedad priority.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriority(Integer value) {
        this.priority = value;
    }

    /**
     * Name of the OSS/BSS user that created the request
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqUser() {
        return reqUser;
    }

    /**
     * Define el valor de la propiedad reqUser.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqUser(String value) {
        this.reqUser = value;
    }

    /**
     * Desired start time of a timed request
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReqStartTime() {
        return reqStartTime;
    }

    /**
     * Define el valor de la propiedad reqStartTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReqStartTime(XMLGregorianCalendar value) {
        this.reqStartTime = value;
    }

    /**
     * The maximum time allowed for a request to be executed. The time is given in minutes or in seconds depending on the InstantLink configuration
     * 
     * @return
     *     possible object is
     *     {@link MaxReqTime }
     *     
     */
    public MaxReqTime getMaxReqTime() {
        return maxReqTime;
    }

    /**
     * Define el valor de la propiedad maxReqTime.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxReqTime }
     *     
     */
    public void setMaxReqTime(MaxReqTime value) {
        this.maxReqTime = value;
    }

    /**
     * Retransmission flag is used to inform InstantLink that the OSS/BSS may have transmitted the message previously. 0 = not transmitted previously, 1 = transmitted previously
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRetransmissionFlag() {
        return retransmissionFlag;
    }

    /**
     * Define el valor de la propiedad retransmissionFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRetransmissionFlag(Boolean value) {
        this.retransmissionFlag = value;
    }

    /**
     * The unique identifier of a request in the OSS/BSS that is set to check for duplicate requests in InstantLink
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetransmissionKey() {
        return retransmissionKey;
    }

    /**
     * Define el valor de la propiedad retransmissionKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetransmissionKey(String value) {
        this.retransmissionKey = value;
    }

    /**
     * Specifies whether request locking is bypassed. true = Request locking is bypassed, false = Request locking is used as normally
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBypassLock() {
        return bypassLock;
    }

    /**
     * Define el valor de la propiedad bypassLock.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBypassLock(Boolean value) {
        this.bypassLock = value;
    }

    /**
     * Specifies whether to include all the raised request processing notifications in the final response message. Notifications may only be generated from asynchronous requests
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddNotificationsToResponse() {
        return addNotificationsToResponse;
    }

    /**
     * Define el valor de la propiedad addNotificationsToResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddNotificationsToResponse(Boolean value) {
        this.addNotificationsToResponse = value;
    }

    /**
     * Selects the notification level threshold for this request. This overrides the value set in the InstantLink configuration. If this parameter is omitted, the value configured in InstantLink is used. 0 = no notifications are created for this request, 3 = Create only BST notifications, 4 = Create BST notifications and NEI phase messages, 5 = Create BST, NEI and system notifications, 9 = Create all possible notifications
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNotificationMsgLevel() {
        return notificationMsgLevel;
    }

    /**
     * Define el valor de la propiedad notificationMsgLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNotificationMsgLevel(Integer value) {
        this.notificationMsgLevel = value;
    }

}
