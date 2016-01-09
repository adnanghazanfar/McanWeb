package com.eamtar.mccn.util;

public class ProjectConstant {
	
	public static final int USER = 0;
	public static final int PHYSICIAN = 1;
	public static final int ADMIN = 5;
	
	
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	public static final int MESSAGE_STATUS_UNREAD = 0;
	public static final int MESSAGE_STATUS_READ = 1;
	public static final int MESSAGE_STATUS_DRAFT = 2;
	
	public static final int STATUS_DELETED = 1;
	public static final int STATUS_NOT_DELETED = 0;
	
	public static final String MESSAGE_TYPE_INBOX = "INBOX";
	public static final String MESSAGE_TYPE_OUTBOX = "OUTBOX";
	public static final String MESSAGE_TYPE_DELETED = "DELETED";
	public static final String MESSAGE_TYPE_COMPOSE = "COMPOSE";
	
	public static final String EMAIL_TYPE_REGISTERED = "REGISTERED";
	public static final String EMAIL_TYPE_FORGOT_PASSWORD = "FORGOT-PASSWORD";
	public static final String EMAIL_TYPE_CHANGE_PASSWORD = "CHANGE-PASSWORD";
	public static final String VIEW_COMPLETE_PROFILE = "VCP";
	public static final String VIEW_QUICK_PROFILE = "VQP";
	
	private ProjectConstant(){}

}
