import { apiClient } from './client';
import { ApiResponse, NotificationItem, PagedResponse } from '../types';

export const notificationApi = {
  getNotifications: async (page = 0, size = 20) => {
    const res = await apiClient.get<ApiResponse<PagedResponse<NotificationItem>>>('/notifications', {
      params: { page, size },
    });
    return res.data.data;
  },

  getUnreadCount: async () => {
    const res = await apiClient.get<ApiResponse<{ unreadCount: number }>>('/notifications/unread-count');
    return res.data.data.unreadCount;
  },

  markAsRead: async (notificationId: number) => {
    await apiClient.patch<ApiResponse<void>>(`/notifications/${notificationId}/read`);
  },
};
