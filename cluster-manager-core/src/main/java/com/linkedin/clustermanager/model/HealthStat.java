package com.linkedin.clustermanager.model;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
//import org.apache.zookeeper.data.Stat;

//import com.linkedin.clustermanager.ZNRecord;
//import com.linkedin.clustermanager.ZNRecordAndStat;
import com.linkedin.clustermanager.ZNRecord;
import com.linkedin.clustermanager.ZNRecordDecorator;
import com.linkedin.clustermanager.model.Message.Attributes;

public class HealthStat extends ZNRecordDecorator {
	//private final ZNRecord _record;

	public enum HealthStatProperty
	{
		FIELDS
	}
	private static final Logger _logger = Logger.getLogger(HealthStat.class.getName());

	public HealthStat(String id)
	{
		super(id);
	}

	public HealthStat(ZNRecord record)
	  {
	    super(record);
	    if(getCreateTimeStamp() == 0)
	    {
	      _record.setSimpleField(Attributes.CREATE_TIMESTAMP.toString(), ""
	          + new Date().getTime());
	    }
	  }
	
	 public long getCreateTimeStamp()
	  {
	    if (_record.getSimpleField(Attributes.CREATE_TIMESTAMP.toString()) == null)
	    {
	      return 0;
	    }
	    try
	    {
	      return Long.parseLong(_record
	          .getSimpleField(Attributes.CREATE_TIMESTAMP.toString()));
	    } catch (Exception e)
	    {
	      return 0;
	    }
	  }
	
	/*
	public HealthStat(ZNRecord record)
	{
		this(record, null);
	}
	
	public HealthStat(ZNRecord record, Stat stat)
	{
		super(record, stat);
	}
	*/
	
	/*
	public String getId()
	{
		return _record.getId();
	}
	*/
	
	public String getTestField()
	{
		return _record.getSimpleField("requestCountStat");
	}
	
	public void setHealthFields(Map<String, Map<String, String>> healthFields)
	{
		_record.setMapFields(healthFields);
	}
	
	public Map<String, Map<String, String>> getHealthFields()
	{
		return _record.getMapFields();
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}