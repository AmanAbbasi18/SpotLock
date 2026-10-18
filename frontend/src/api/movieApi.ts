import { apiClient } from './client';
import { ApiResponse, Movie, PagedResponse } from '../types';

export const movieApi = {
  getMovies: async (params?: {
    genre?: string;
    language?: string;
    search?: string;
    city?: string;
    page?: number;
    size?: number;
  }) => {
    const res = await apiClient.get<ApiResponse<PagedResponse<Movie>>>('/movies', { params });
    return res.data.data;
  },

  getMovieById: async (movieId: number) => {
    const res = await apiClient.get<ApiResponse<Movie>>(`/movies/${movieId}`);
    return res.data.data;
  },

  adminGetAllMovies: async (params?: { status?: string; search?: string; page?: number; size?: number }) => {
    const res = await apiClient.get<ApiResponse<PagedResponse<Movie>>>('/admin/movies', { params });
    return res.data.data;
  },

  adminCreateMovie: async (data: Partial<Movie>) => {
    const res = await apiClient.post<ApiResponse<Movie>>('/admin/movies', data);
    return res.data.data;
  },

  adminUpdateMovie: async (movieId: number, data: Partial<Movie>) => {
    const res = await apiClient.put<ApiResponse<Movie>>(`/admin/movies/${movieId}`, data);
    return res.data.data;
  },

  adminUpdateStatus: async (movieId: number, status: 'ACTIVE' | 'INACTIVE') => {
    const res = await apiClient.patch<ApiResponse<Movie>>(`/admin/movies/${movieId}/status`, null, {
      params: { status },
    });
    return res.data.data;
  },
};
