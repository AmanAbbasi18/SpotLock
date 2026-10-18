import { apiClient } from './client';
import { ApiResponse, Show, ShowSeatAvailabilityResponse } from '../types';

export const showApi = {
  getShowsForMovie: async (movieId: number, city?: string) => {
    const res = await apiClient.get<ApiResponse<Show[]>>(`/movies/${movieId}/shows`, {
      params: { city },
    });
    return res.data.data;
  },

  getShowById: async (showId: number) => {
    const res = await apiClient.get<ApiResponse<Show>>(`/shows/${showId}`);
    return res.data.data;
  },

  getSeatAvailability: async (showId: number) => {
    const res = await apiClient.get<ApiResponse<ShowSeatAvailabilityResponse>>(`/shows/${showId}/seats`);
    return res.data.data;
  },

  adminCreateShow: async (data: {
    movieId: number;
    screenId: number;
    startTime: string;
    endTime: string;
    basePrice: number;
  }) => {
    const res = await apiClient.post<ApiResponse<Show>>('/admin/shows', data);
    return res.data.data;
  },

  adminCancelShow: async (showId: number) => {
    const res = await apiClient.patch<ApiResponse<Show>>(`/admin/shows/${showId}/cancel`);
    return res.data.data;
  },
};
