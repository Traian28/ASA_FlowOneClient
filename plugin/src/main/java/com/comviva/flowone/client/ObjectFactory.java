
package main.java.com.comviva.flowone.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the main.java.com.comviva.flowone.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NeType_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "NeType");
    private final static QName _OrderNo_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "OrderNo");
    private final static QName _Priority_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "Priority");
    private final static QName _ReqUser_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "ReqUser");
    private final static QName _ReqStartTime_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "ReqStartTime");
    private final static QName _MaxReqTime_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "MaxReqTime");
    private final static QName _RetransmissionFlag_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "RetransmissionFlag");
    private final static QName _RetransmissionKey_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "RetransmissionKey");
    private final static QName _BypassLock_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "BypassLock");
    private final static QName _AddNotificationsToResponse_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "AddNotificationsToResponse");
    private final static QName _NotificationMsgLevel_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "NotificationMsgLevel");
    private final static QName _RequestId_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "RequestId");
    private final static QName _Status_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "Status");
    private final static QName _StatusMessage_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "StatusMessage");
    private final static QName _StatusMessageId_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "StatusMessageId");
    private final static QName _ReceivedDate_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "ReceivedDate");
    private final static QName _FinishedDate_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "FinishedDate");
    private final static QName _Level_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "Level");
    private final static QName _InstantLinkAvailabilityInfo_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "InstantLinkAvailabilityInfo");
    private final static QName _Response_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "Response");
    private final static QName _Notification_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "Notification");
    private final static QName _ResponseAcknowledgement_QNAME = new QName("http://soa.comptel.com/2011/02/instantlink", "ResponseAcknowledgement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: main.java.com.comviva.flowone.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link MaxReqTime }
     * 
     */
    public MaxReqTime createMaxReqTime() {
        return new MaxReqTime();
    }

    /**
     * Create an instance of {@link InstantLinkAvailabilityInfo }
     * 
     */
    public InstantLinkAvailabilityInfo createInstantLinkAvailabilityInfo() {
        return new InstantLinkAvailabilityInfo();
    }

    /**
     * Create an instance of {@link Notification }
     * 
     */
    public Notification createNotification() {
        return new Notification();
    }

    /**
     * Create an instance of {@link DeleteRequest }
     * 
     */
    public DeleteRequest createDeleteRequest() {
        return new DeleteRequest();
    }

    /**
     * Create an instance of {@link RequestHeader }
     * 
     */
    public RequestHeader createRequestHeader() {
        return new RequestHeader();
    }

    /**
     * Create an instance of {@link main.java.com.comviva.flowone.client.InstantLinkRequest.RequestParameters }
     * 
     */
    public main.java.com.comviva.flowone.client.InstantLinkRequest.RequestParameters createInstantLinkRequestRequestParameters() {
        return new main.java.com.comviva.flowone.client.InstantLinkRequest.RequestParameters();
    }

    /**
     * Create an instance of {@link DisplayRequest }
     * 
     */
    public DisplayRequest createDisplayRequest() {
        return new DisplayRequest();
    }

    /**
     * Create an instance of {@link CreateRequest }
     * 
     */
    public CreateRequest createCreateRequest() {
        return new CreateRequest();
    }

    /**
     * Create an instance of {@link ModifyRequest }
     * 
     */
    public ModifyRequest createModifyRequest() {
        return new ModifyRequest();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link ResponseHeader }
     * 
     */
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    /**
     * Create an instance of {@link Response.ResponseParameters }
     * 
     */
    public Response.ResponseParameters createResponseResponseParameters() {
        return new Response.ResponseParameters();
    }

    /**
     * Create an instance of {@link Response.RequestParameters }
     * 
     */
    public Response.RequestParameters createResponseRequestParameters() {
        return new Response.RequestParameters();
    }

    /**
     * Create an instance of {@link Response.Notifications }
     * 
     */
    public Response.Notifications createResponseNotifications() {
        return new Response.Notifications();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "NeType")
    public JAXBElement<String> createNeType(String value) {
        return new JAXBElement<String>(_NeType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "OrderNo")
    public JAXBElement<String> createOrderNo(String value) {
        return new JAXBElement<String>(_OrderNo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "Priority")
    public JAXBElement<Integer> createPriority(Integer value) {
        return new JAXBElement<Integer>(_Priority_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "ReqUser")
    public JAXBElement<String> createReqUser(String value) {
        return new JAXBElement<String>(_ReqUser_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "ReqStartTime")
    public JAXBElement<XMLGregorianCalendar> createReqStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReqStartTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MaxReqTime }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MaxReqTime }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "MaxReqTime")
    public JAXBElement<MaxReqTime> createMaxReqTime(MaxReqTime value) {
        return new JAXBElement<MaxReqTime>(_MaxReqTime_QNAME, MaxReqTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "RetransmissionFlag")
    public JAXBElement<Boolean> createRetransmissionFlag(Boolean value) {
        return new JAXBElement<Boolean>(_RetransmissionFlag_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "RetransmissionKey")
    public JAXBElement<String> createRetransmissionKey(String value) {
        return new JAXBElement<String>(_RetransmissionKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "BypassLock")
    public JAXBElement<Boolean> createBypassLock(Boolean value) {
        return new JAXBElement<Boolean>(_BypassLock_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "AddNotificationsToResponse")
    public JAXBElement<Boolean> createAddNotificationsToResponse(Boolean value) {
        return new JAXBElement<Boolean>(_AddNotificationsToResponse_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "NotificationMsgLevel")
    public JAXBElement<Integer> createNotificationMsgLevel(Integer value) {
        return new JAXBElement<Integer>(_NotificationMsgLevel_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "RequestId")
    public JAXBElement<Long> createRequestId(Long value) {
        return new JAXBElement<Long>(_RequestId_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "Status")
    public JAXBElement<Integer> createStatus(Integer value) {
        return new JAXBElement<Integer>(_Status_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "StatusMessage")
    public JAXBElement<String> createStatusMessage(String value) {
        return new JAXBElement<String>(_StatusMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "StatusMessageId")
    public JAXBElement<String> createStatusMessageId(String value) {
        return new JAXBElement<String>(_StatusMessageId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "ReceivedDate")
    public JAXBElement<XMLGregorianCalendar> createReceivedDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReceivedDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "FinishedDate")
    public JAXBElement<XMLGregorianCalendar> createFinishedDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_FinishedDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "Level")
    public JAXBElement<Integer> createLevel(Integer value) {
        return new JAXBElement<Integer>(_Level_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantLinkAvailabilityInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InstantLinkAvailabilityInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "InstantLinkAvailabilityInfo")
    public JAXBElement<InstantLinkAvailabilityInfo> createInstantLinkAvailabilityInfo(InstantLinkAvailabilityInfo value) {
        return new JAXBElement<InstantLinkAvailabilityInfo>(_InstantLinkAvailabilityInfo_QNAME, InstantLinkAvailabilityInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "Response")
    public JAXBElement<Response> createResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Notification }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Notification }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "Notification")
    public JAXBElement<Notification> createNotification(Notification value) {
        return new JAXBElement<Notification>(_Notification_QNAME, Notification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.comptel.com/2011/02/instantlink", name = "ResponseAcknowledgement")
    public JAXBElement<String> createResponseAcknowledgement(String value) {
        return new JAXBElement<String>(_ResponseAcknowledgement_QNAME, String.class, null, value);
    }

}
