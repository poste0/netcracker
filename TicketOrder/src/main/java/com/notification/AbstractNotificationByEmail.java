package notification;

import notification.Notification;

public abstract class AbstractNotificationByEmail implements Notification
{
	public AbstractNotificationByEmail() {
	}

	private String email;

	public abstract void notifyUser();
}