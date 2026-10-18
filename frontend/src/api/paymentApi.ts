import { apiClient } from './client';
import { ApiResponse, PaymentResponse } from '../types';

export const paymentApi = {
  processPayment: async (
    bookingId: number,
    data: {
      idempotencyKey: string;
      simulationOutcome?: string;
      paymentMethod?: string;
    }
  ) => {
    const res = await apiClient.post<ApiResponse<PaymentResponse>>(`/bookings/${bookingId}/payments`, data);
    return res.data.data;
  },

  getPaymentById: async (paymentId: number) => {
    const res = await apiClient.get<ApiResponse<PaymentResponse>>(`/payments/${paymentId}`);
    return res.data.data;
  },
};
