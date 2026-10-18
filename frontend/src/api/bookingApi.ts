import { apiClient } from './client';
import { ApiResponse, BookingResponse, PagedResponse } from '../types';

export const bookingApi = {
  createBooking: async (holdId: number) => {
    const res = await apiClient.post<ApiResponse<BookingResponse>>('/bookings', { holdId });
    return res.data.data;
  },

  getUserBookings: async (page = 0, size = 10) => {
    const res = await apiClient.get<ApiResponse<PagedResponse<BookingResponse>>>('/bookings', {
      params: { page, size },
    });
    return res.data.data;
  },

  getBookingById: async (bookingId: number) => {
    const res = await apiClient.get<ApiResponse<BookingResponse>>(`/bookings/${bookingId}`);
    return res.data.data;
  },

  getBookingByReference: async (reference: string) => {
    const res = await apiClient.get<ApiResponse<BookingResponse>>(`/bookings/reference/${reference}`);
    return res.data.data;
  },

  cancelBooking: async (bookingId: number) => {
    const res = await apiClient.post<ApiResponse<BookingResponse>>(`/bookings/${bookingId}/cancel`);
    return res.data.data;
  },
};
