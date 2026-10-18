import { apiClient } from './client';
import { ApiResponse, AuthResponse, User } from '../types';

export const authApi = {
  register: async (data: { name: string; email: string; password: string }) => {
    const res = await apiClient.post<ApiResponse<AuthResponse>>('/auth/register', data);
    return res.data.data;
  },

  login: async (data: { email: string; password: string }) => {
    const res = await apiClient.post<ApiResponse<AuthResponse>>('/auth/login', data);
    return res.data.data;
  },

  logout: async (refreshToken?: string) => {
    try {
      await apiClient.post<ApiResponse<void>>('/auth/logout', { refreshToken });
    } catch {
      // Ignore network errors on logout
    }
  },

  getCurrentUser: async () => {
    const res = await apiClient.get<ApiResponse<User>>('/users/me');
    return res.data.data;
  },
};
