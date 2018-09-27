package com.notification;

/**
 * Contains email of the user. It's abstract where methos notifyUser is not created.
 */
public abstract class AbstractNotificationByEmail implements Notification {
	public AbstractNotificationByEmail() {
	}

	private String email;

	public abstract void notifyUser();
}