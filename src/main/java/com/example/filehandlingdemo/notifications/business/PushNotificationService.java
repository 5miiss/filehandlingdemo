package com.example.filehandlingdemo.notifications.business;

import com.example.filehandlingdemo.notifications.entities.Notification;

public interface PushNotificationService {
    void sendMessageToUser(String message, String userId, String data);

    void notifyMultiUsers(Notification notification);
}
