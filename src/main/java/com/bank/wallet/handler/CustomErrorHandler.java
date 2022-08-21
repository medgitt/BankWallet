package com.bank.wallet.handler;


public class CustomErrorHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public CustomErrorHandler( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CustomErrorHandler( String message, Object fieldValue) {
        super(String.format(message + " : '%s'", fieldValue));
        this.fieldValue = fieldValue;
    }
    
    public CustomErrorHandler( String message) {
        super(String.format(message));
    }
    
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
