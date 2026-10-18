import { apiClient } from './client';
import { ApiResponse, Cinema, Screen } from '../types';

export const cinemaApi = {
  getCities: async () => {
    const res = await apiClient.get<ApiResponse<string[]>>('/cities');
    return res.data.data;
  },

  getCinemas: async (city?: string) => {
    const res = await apiClient.get<ApiResponse<Cinema[]>>('/cinemas', { params: { city } });
    return res.data.data;
  },

  getCinemaById: async (cinemaId: number) => {
    const res = await apiClient.get<ApiResponse<Cinema>>(`/cinemas/${cinemaId}`);
    return res.data.data;
  },

  getScreensByCinema: async (cinemaId: number) => {
    const res = await apiClient.get<ApiResponse<Screen[]>>(`/cinemas/${cinemaId}/screens`);
    return res.data.data;
  },

  adminCreateCinema: async (data: { name: string; address: string; city: string }) => {
    const res = await apiClient.post<ApiResponse<Cinema>>('/admin/cinemas', data);
    return res.data.data;
  },

  adminCreateScreen: async (cinemaId: number, data: { name: string; totalSeats: number }) => {
    const res = await apiClient.post<ApiResponse<Screen>>(`/admin/cinemas/${cinemaId}/screens`, data);
    return res.data.data;
  },
};
