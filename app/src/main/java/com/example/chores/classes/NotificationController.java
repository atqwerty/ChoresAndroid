package com.example.chores.classes;

import java.util.ArrayList;

public class NotificationController {
    private ArrayList<Notification> notifications;
    private User currentUser;

    public NotificationController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }
}
