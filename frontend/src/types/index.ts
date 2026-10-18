export type Role = 'ROLE_CUSTOMER' | 'ROLE_ADMIN' | 'ROLE_SYSTEM';

export interface User {
  id: number;
  name: string;
  email: string;
  role: Role;
  enabled: boolean;
  createdAt: string;
}

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresInMs: number;
  user: User;
}

export type MovieStatus = 'ACTIVE' | 'INACTIVE';

export interface Movie {
  id: number;
  title: string;
  description: string;
  durationMinutes: number;
  language: string;
  genre: string;
  releaseDate: string;
  posterUrl: string;
  contentRating: string;
  status: MovieStatus;
  createdAt: string;
}

export interface Cinema {
  id: number;
  name: string;
  address: string;
  city: string;
  status: string;
  createdAt: string;
}

export interface Screen {
  id: number;
  cinemaId: number;
  name: string;
  totalSeats: number;
  status: string;
}

export type SeatType = 'SILVER' | 'GOLD' | 'PLATINUM';

export type ShowSeatStatus = 'AVAILABLE' | 'HELD' | 'BOOKED';

export interface ShowSeat {
  showSeatId: number;
  seatId: number;
  rowLabel: string;
  seatNumber: number;
  seatType: SeatType;
  price: number;
  status: ShowSeatStatus;
  isHeldByCurrentUser: boolean;
}

export interface ShowSeatAvailabilityResponse {
  showId: number;
  totalSeats: number;
  availableSeats: number;
  heldSeats: number;
  bookedSeats: number;
  seats: ShowSeat[];
}

export type ShowStatus = 'SCHEDULED' | 'CANCELLED' | 'COMPLETED';

export interface Show {
  id: number;
  movieId: number;
  movieTitle: string;
  moviePosterUrl: string;
  movieLanguage: string;
  movieGenre: string;
  cinemaId: number;
  cinemaName: string;
  cinemaCity: string;
  screenId: number;
  screenName: string;
  startTime: string;
  endTime: string;
  basePrice: number;
  status: ShowStatus;
}

export type HoldStatus = 'ACTIVE' | 'CONFIRMED' | 'EXPIRED' | 'RELEASED';

export interface SeatHoldResponse {
  holdId: number;
  showId: number;
  userId: number;
  seatIds: number[];
  totalAmount: number;
  status: HoldStatus;
  createdAt: string;
  expiresAt: string;
  expiresInSeconds: number;
}

export type BookingStatus = 'PENDING' | 'CONFIRMED' | 'PAYMENT_FAILED' | 'EXPIRED' | 'CANCELLED';

export interface BookingSeat {
  showSeatId: number;
  rowLabel: string;
  seatNumber: number;
  seatType: SeatType;
  price: number;
}

export interface BookingResponse {
  id: number;
  bookingReference: string;
  userId: number;
  showId: number;
  movieTitle: string;
  moviePosterUrl: string;
  cinemaName: string;
  cinemaAddress: string;
  cinemaCity: string;
  screenName: string;
  startTime: string;
  endTime: string;
  status: BookingStatus;
  totalAmount: number;
  currency: string;
  seats: BookingSeat[];
  createdAt: string;
  expiresAt: string;
}

export type PaymentStatus = 'INITIATED' | 'SUCCESS' | 'FAILED' | 'REFUND_PENDING' | 'REFUNDED';

export interface PaymentResponse {
  id: number;
  bookingId: number;
  bookingReference: string;
  paymentReference: string;
  idempotencyKey: string;
  amount: number;
  currency: string;
  status: PaymentStatus;
  providerReference?: string;
  failureReason?: string;
  bookingStatus: BookingStatus;
  createdAt: string;
}

export interface NotificationItem {
  id: number;
  title: string;
  message: string;
  type: string;
  isRead: boolean;
  createdAt: string;
}

export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  errorCode?: string;
  errors?: string[];
  timestamp: string;
}

export interface PagedResponse<T> {
  content: T[];
  pageNumber: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  isLast: boolean;
}
