
package com.comviva.executor.body;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GeneralBodyResponse extends GeneralBodyRequest
{
  
  private String              resultCode;
  private String              resultDescription;
  private Map<String, String> resultData;
  
  public GeneralBodyResponse()
  {
    super();
    this.resultData = new HashMap<String, String>();
  }
  
  public GeneralBodyResponse( String resultCode, String resultDescription, Map<String, String> resultData )
  {
    super();
    this.resultCode = resultCode;
    this.resultDescription = resultDescription;
    this.resultData = resultData;
  }
  
  public GeneralBodyResponse( String serviceInfoServiceName, String serviceInfoOperationName, String serviceInfoUrl, Map<String, String> parameters, String resultCode, String resultDescription, Map<String, String> resultData )
  {
    super(serviceInfoServiceName, serviceInfoOperationName, serviceInfoUrl, parameters);
    this.resultCode = resultCode;
    this.resultDescription = resultDescription;
    this.resultData = resultData;
  }
  
  public String getResultCode()
  {
    return resultCode;
  }
  
  public void setResultCode( String resultCode )
  {
    this.resultCode = resultCode;
  }
  
  public String getResultDescription()
  {
    return resultDescription;
  }
  
  public void setResultDescription( String resultDescription )
  {
    this.resultDescription = resultDescription;
  }
  
  public Map<String, String> getResultData()
  {
    return resultData;
  }
  
  public void setResultData( Map<String, String> resultData )
  {
    this.resultData = resultData;
  }
  
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((resultCode == null) ? 0 : resultCode.hashCode());
    result = prime * result + ((resultData == null) ? 0 : resultData.hashCode());
    result = prime * result + ((resultDescription == null) ? 0 : resultDescription.hashCode());
    return result;
  }
  
  @Override
  public boolean equals( Object obj )
  {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    GeneralBodyResponse other = (GeneralBodyResponse) obj;
    if (resultCode == null)
    {
      if (other.resultCode != null) return false;
    }
    else if (!resultCode.equals(other.resultCode)) return false;
    if (resultData == null)
    {
      if (other.resultData != null) return false;
    }
    else if (!resultData.equals(other.resultData)) return false;
    if (resultDescription == null)
    {
      if (other.resultDescription != null) return false;
    }
    else if (!resultDescription.equals(other.resultDescription)) return false;
    return true;
  }
  
  @Override
  public String toString()
  {
    
    StringBuilder parametersSB = new StringBuilder();
    parametersSB.append(" {");
    if (this.getParameters() != null)
    {
      Set<String> keySet = this.getParameters().keySet();
      int i = 1;
      for (String key : keySet)
      {
        parametersSB.append(" \"" + key + "\" : \"" + this.getParameters().get(key) + "\" ");
        if (i < this.getParameters().size())
        {
          parametersSB.append("|");
        }
      }
    }
    parametersSB.append("} ");
    
    StringBuilder resultDataSB = new StringBuilder();
    resultDataSB.append(" {");
    if (this.resultData != null)
    {
      Set<String> keySet = this.resultData.keySet();
      int i = 1;
      for (String key : keySet)
      {
        resultDataSB.append(" \"" + key + "\" : \"" + this.resultData.get(key) + "\" ");
        if (i < this.resultData.size())
        {
          resultDataSB.append("|");
        }
      }
    }
    resultDataSB.append("} ");
    
    return "GeneralBodyResponse [" + "serviceInfoServiceName=" + getServiceInfoServiceName() + ", serviceInfoOperationName=" + getServiceInfoOperationName() + ", serviceInfoUrl=" + getServiceInfoUrl() + ", parameters=" + parametersSB + ", resultCode=" + resultCode + ", resultDescription=" + resultDescription + ", resultData=" + resultDataSB + "]";
  }
  
}
