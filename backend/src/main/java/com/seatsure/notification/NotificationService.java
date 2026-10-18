package com.seatsure.notification;

import com.seatsure.common.exception.ResourceNotFoundException;
import com.seatsure.common.response.PagedResponse;
import com.seatsure.common.util.SecurityUtils;
import com.seatsure.notification.dto.NotificationDto;
import com.seatsure.user.User;
import com.seatsure.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NotificationService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public PagedResponse<NotificationDto> getCurrentUserNotifications(Pageable pageable) {
        Long userId = SecurityUtils.getCurrentUserId();
        Page<Notification> page = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        List<NotificationDto> content = page.getContent().stream().map(NotificationDto::fromEntity).toList();
        return PagedResponse.<NotificationDto>builder().content(content).pageNumber(page.getNumber()).pageSize(page.getSize()).totalElements(page.getTotalElements()).totalPages(page.getTotalPages()).isLast(page.isLast()).build();
    }

    @Transactional(readOnly = true)
    public long getUnreadCount() {
        Long userId = SecurityUtils.getCurrentUserId();
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        Long userId = SecurityUtils.getCurrentUserId();
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", notificationId));
        if (!notification.getUser().getId().equals(userId)) {
            log.warn("User {} attempted to mark notification {} belonging to another user", userId, notificationId);
            return;
        }
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    @Transactional
    public void createNotification(Long userId, String title, String message, String type) {
        userRepository.findById(userId).ifPresent(user -> {
            Notification notification = Notification.builder().user(user).title(title).message(message).type(type).isRead(false).build();
            notificationRepository.save(notification);
            log.info("Created notification for user {}: {}", userId, title);
        });
    }
}
