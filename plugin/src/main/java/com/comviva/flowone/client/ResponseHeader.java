
package main.java.com.comviva.flowone.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ResponseHeader identifies the response to a certain request. It also contains status information of the processed request.
 * 
 * <p>Clase Java para ResponseHeader complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}RequestId"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}Status"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}OrderNo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}StatusMessage" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}StatusMessageId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}Priority" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}ReqUser" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}ReceivedDate" minOccurs="0"/&gt;
 *         &lt;element ref="{http://soa.comptel.com/2011/02/instantlink}FinishedDate" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseHeader", propOrder = {
    "requestId",
    "status",
    "orderNo",
    "statusMessage",
    "statusMessageId",
    "priority",
    "reqUser",
    "receivedDate",
    "finishedDate"
})
public class ResponseHeader {

    @XmlElement(name = "RequestId")
    protected long requestId;
    @XmlElement(name = "Status")
    protected int status;
    @XmlElement(name = "OrderNo")
    protected String orderNo;
    @XmlElement(name = "StatusMessage")
    protected String statusMessage;
    @XmlElement(name = "StatusMessageId")
    protected String statusMessageId;
    @XmlElement(name = "Priority")
    protected Integer priority;
    @XmlElement(name = "ReqUser")
    protected String reqUser;
    @XmlElement(name = "ReceivedDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar receivedDate;
    @XmlElement(name = "FinishedDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar finishedDate;

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
     * Request status: 0 = request succesfully received (but not yet executed), 1 = request was not delivered to InstantLink, resend is needed, 2 = request was corrupted and not accepted, 3 = user was not authenticated correctly, request is not accepted, 6 = request was cancelled, 7 = request execution was partly failed, 8 = request execution was failed, 9 = request was executed succesfully, 10 = request was rejected during processing inside IL
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
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
     * Status message related to the request. If the request is failed, this is the corresponding error message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Define el valor de la propiedad statusMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Id of the status message
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessageId() {
        return statusMessageId;
    }

    /**
     * Define el valor de la propiedad statusMessageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessageId(String value) {
        this.statusMessageId = value;
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
     * The date when request was received by InstantLink
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReceivedDate() {
        return receivedDate;
    }

    /**
     * Define el valor de la propiedad receivedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReceivedDate(XMLGregorianCalendar value) {
        this.receivedDate = value;
    }

    /**
     * The date when request execution was finished in InstantLink
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinishedDate() {
        return finishedDate;
    }

    /**
     * Define el valor de la propiedad finishedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinishedDate(XMLGregorianCalendar value) {
        this.finishedDate = value;
    }

}
