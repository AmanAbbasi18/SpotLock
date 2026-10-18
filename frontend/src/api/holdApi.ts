import { apiClient } from './client';
import { ApiResponse, SeatHoldResponse } from '../types';

export const holdApi = {
  createHold: async (showId: number, seatIds: number[]) => {
    const res = await apiClient.post<ApiResponse<SeatHoldResponse>>(`/shows/${showId}/holds`, { seatIds });
    return res.data.data;
  },

  getHoldById: async (holdId: number) => {
    const res = await apiClient.get<ApiResponse<SeatHoldResponse>>(`/holds/${holdId}`);
    return res.data.data;
  },

  releaseHold: async (holdId: number) => {
    await apiClient.delete<ApiResponse<void>>(`/holds/${holdId}`);
  },
};
