package io.mohkeita.notification.service;

import io.mohkeita.clients.notification.NotificationRequest;
import io.mohkeita.notification.model.Notification;
import io.mohkeita.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("mohkeita")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );

    }
}
