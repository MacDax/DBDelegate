package com.spring.boot.personsdb.transactions;

public class ServicesDTO {

	private int serviceId;
	private String serviceType;
	private String serviceName;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "ServicesDTO [serviceId=" + serviceId + ", serviceType=" + serviceType + ", serviceName=" + serviceName
				+ "]";
	}

}
