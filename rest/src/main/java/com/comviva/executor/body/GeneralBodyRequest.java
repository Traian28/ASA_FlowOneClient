
package com.comviva.executor.body;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GeneralBodyRequest
{
  
  private String              serviceInfoServiceName;
  private String              serviceInfoOperationName;
  private String              serviceInfoUrl;
  
  private Map<String, String> parameters;
  
  public GeneralBodyRequest()
  {
    super();
    this.parameters = new HashMap<String, String>();
  }
  
  public GeneralBodyRequest( String serviceInfoServiceName, String serviceInfoOperationName, String serviceInfoUrl, Map<String, String> parameters )
  {
    super();
    this.serviceInfoServiceName = serviceInfoServiceName;
    this.serviceInfoOperationName = serviceInfoOperationName;
    this.serviceInfoUrl = serviceInfoUrl;
    this.parameters = parameters;
  }
  
  public String getServiceInfoServiceName()
  {
    return serviceInfoServiceName;
  }
  
  public void setServiceInfoServiceName( String serviceInfoServiceName )
  {
    this.serviceInfoServiceName = serviceInfoServiceName;
  }
  
  public String getServiceInfoOperationName()
  {
    return serviceInfoOperationName;
  }
  
  public void setServiceInfoOperationName( String serviceInfoOperationName )
  {
    this.serviceInfoOperationName = serviceInfoOperationName;
  }
  
  public String getServiceInfoUrl()
  {
    return serviceInfoUrl;
  }
  
  public void setServiceInfoUrl( String serviceInfoUrl )
  {
    this.serviceInfoUrl = serviceInfoUrl;
  }
  
  public Map<String, String> getParameters()
  {
    return parameters;
  }
  
  public void setParameters( Map<String, String> parameters )
  {
    this.parameters = parameters;
  }
  
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
    result = prime * result + ((serviceInfoOperationName == null) ? 0 : serviceInfoOperationName.hashCode());
    result = prime * result + ((serviceInfoServiceName == null) ? 0 : serviceInfoServiceName.hashCode());
    result = prime * result + ((serviceInfoUrl == null) ? 0 : serviceInfoUrl.hashCode());
    return result;
  }
  
  @Override
  public boolean equals( Object obj )
  {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    GeneralBodyRequest other = (GeneralBodyRequest) obj;
    if (parameters == null)
    {
      if (other.parameters != null) return false;
    }
    else if (!parameters.equals(other.parameters)) return false;
    if (serviceInfoOperationName == null)
    {
      if (other.serviceInfoOperationName != null) return false;
    }
    else if (!serviceInfoOperationName.equals(other.serviceInfoOperationName)) return false;
    if (serviceInfoServiceName == null)
    {
      if (other.serviceInfoServiceName != null) return false;
    }
    else if (!serviceInfoServiceName.equals(other.serviceInfoServiceName)) return false;
    if (serviceInfoUrl == null)
    {
      if (other.serviceInfoUrl != null) return false;
    }
    else if (!serviceInfoUrl.equals(other.serviceInfoUrl)) return false;
    return true;
  }
  
  @Override
  public String toString()
  {
    StringBuilder parametersSB = new StringBuilder();
    parametersSB.append(" {");
    if (this.parameters != null)
    {
      Set<String> keySet = this.parameters.keySet();
      int i = 1;
      for (String key : keySet)
      {
        parametersSB.append(" \"" + key + "\" : \"" + this.parameters.get(key) + "\" ");
        if (i < this.parameters.size())
        {
          parametersSB.append("|");
        }
      }
    }
    parametersSB.append("} ");
    
    return "GeneralBodyRequest [serviceInfoServiceName=" + serviceInfoServiceName + ", serviceInfoOperationName=" + serviceInfoOperationName + ", serviceInfoUrl=" + serviceInfoUrl + ", parameters=" + parametersSB.toString() + "]";
  }
  
}