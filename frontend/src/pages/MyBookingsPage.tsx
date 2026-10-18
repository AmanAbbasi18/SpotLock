import React, { useEffect, useState } from 'react';
import { bookingApi } from '../api/bookingApi';
import { BookingResponse } from '../types';
import { Ticket, Calendar, Clock, AlertCircle, CheckCircle } from 'lucide-react';
import { Link } from 'react-router-dom';

export const MyBookingsPage: React.FC = () => {
  const [bookings, setBookings] = useState<BookingResponse[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [activeTab, setActiveTab] = useState<'ALL' | 'CONFIRMED' | 'CANCELLED'>('ALL');
  const [selectedBookingForCancel, setSelectedBookingForCancel] = useState<BookingResponse | null>(null);
  const [cancelling, setCancelling] = useState<boolean>(false);
  const [actionMessage, setActionMessage] = useState<{ text: string; type: 'success' | 'error' } | null>(null);

  const fetchBookings = async () => {
    setLoading(true);
    try {
      const res = await bookingApi.getUserBookings(0, 50);
      setBookings(res.content);
    } catch (err) {
      console.error('Failed to load bookings', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchBookings();
  }, []);

  const handleCancelBooking = async () => {
    if (!selectedBookingForCancel) return;

    setCancelling(true);
    setActionMessage(null);

    try {
      await bookingApi.cancelBooking(selectedBookingForCancel.id);
      setActionMessage({
        text: `Booking ${selectedBookingForCancel.bookingReference} has been cancelled. Refund of ₹${selectedBookingForCancel.totalAmount} initiated.`,
        type: 'success',
      });
      setSelectedBookingForCancel(null);
      fetchBookings();
    } catch (err: any) {
      const msg = err.response?.data?.message || 'Failed to cancel booking. It may be past the cancellation deadline.';
      setActionMessage({ text: msg, type: 'error' });
    } finally {
      setCancelling(false);
    }
  };

  const filteredBookings = bookings.filter((b) => {
    if (activeTab === 'CONFIRMED') return b.status === 'CONFIRMED';
    if (activeTab === 'CANCELLED') return b.status === 'CANCELLED';
    return true;
  });

  const formatTime = (iso: string) => new Date(iso).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  const formatDate = (iso: string) => new Date(iso).toLocaleDateString([], { weekday: 'short', month: 'short', day: 'numeric' });

  return (
    <div style={{ maxWidth: '1000px', margin: '0 auto', padding: '24px' }}>
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '28px', flexWrap: 'wrap', gap: '16px' }}>
        <div>
          <h1 style={{ fontSize: '2rem', fontWeight: 800, marginBottom: '6px' }}>
            My Bookings
          </h1>
          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
            View your tickets, screening times, and cancellation policies.
          </p>
        </div>

        {/* Tab Filters */}
        <div style={{ display: 'flex', gap: '8px', background: 'var(--bg-surface-elevated)', padding: '4px', borderRadius: 'var(--radius-md)' }}>
          {(['ALL', 'CONFIRMED', 'CANCELLED'] as const).map((tab) => (
            <button
              key={tab}
              onClick={() => setActiveTab(tab)}
              style={{
                padding: '6px 16px',
                borderRadius: '6px',
                fontSize: '0.85rem',
                fontWeight: 600,
                background: activeTab === tab ? 'var(--accent-primary)' : 'transparent',
                color: activeTab === tab ? '#fff' : 'var(--text-muted)',
              }}
            >
              {tab}
            </button>
          ))}
        </div>
      </div>

      {actionMessage && (
        <div
          style={{
            background: actionMessage.type === 'success' ? 'rgba(16, 185, 129, 0.15)' : 'rgba(239, 68, 68, 0.15)',
            border: `1px solid ${actionMessage.type === 'success' ? '#10b981' : '#ef4444'}`,
            color: actionMessage.type === 'success' ? '#34d399' : '#f87171',
            padding: '14px 20px',
            borderRadius: 'var(--radius-md)',
            marginBottom: '24px',
            display: 'flex',
            alignItems: 'center',
            gap: '12px',
          }}
        >
          {actionMessage.type === 'success' ? <CheckCircle size={20} /> : <AlertCircle size={20} />}
          <span>{actionMessage.text}</span>
        </div>
      )}

      {loading ? (
        <div style={{ textAlign: 'center', padding: '60px 0', color: 'var(--text-muted)' }}>
          Loading your booking history...
        </div>
      ) : filteredBookings.length === 0 ? (
        <div className="glass-panel" style={{ padding: '60px 24px', textAlign: 'center' }}>
          <Ticket size={48} color="var(--text-dim)" style={{ marginBottom: '16px' }} />
          <h3 style={{ fontSize: '1.25rem', marginBottom: '8px' }}>No Bookings Found</h3>
          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', marginBottom: '24px' }}>
            You have no {activeTab !== 'ALL' ? activeTab.toLowerCase() : ''} bookings in your account history.
          </p>
          <Link to="/" className="btn-primary">
            Explore Movies & Book
          </Link>
        </div>
      ) : (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
          {filteredBookings.map((b) => {
            const isConfirmed = b.status === 'CONFIRMED';
            const isCancelled = b.status === 'CANCELLED';

            return (
              <div
                key={b.id}
                className="glass-panel"
                style={{
                  padding: '24px',
                  display: 'flex',
                  gap: '24px',
                  alignItems: 'center',
                  flexWrap: 'wrap',
                  borderColor: isConfirmed ? 'rgba(99, 102, 241, 0.3)' : 'var(--border-subtle)',
                }}
              >
                <img
                  src={b.moviePosterUrl || 'https://images.unsplash.com/photo-1534447677768-be436bb09401?w=300'}
                  alt={b.movieTitle}
                  style={{ width: '90px', height: '135px', objectFit: 'cover', borderRadius: 'var(--radius-md)' }}
                />

                <div style={{ flex: 1, minWidth: '240px' }}>
                  <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '8px' }}>
                    <span style={{ fontFamily: 'monospace', fontWeight: 700, fontSize: '0.95rem', color: '#38bdf8' }}>
                      {b.bookingReference}
                    </span>
                    <span
                      className={`badge ${
                        isConfirmed ? 'badge-success' : isCancelled ? 'badge-danger' : 'badge-warning'
                      }`}
                    >
                      {b.status}
                    </span>
                  </div>

                  <h3 style={{ fontSize: '1.25rem', fontWeight: 800, marginBottom: '6px' }}>
                    {b.movieTitle}
                  </h3>

                  <div style={{ color: 'var(--text-muted)', fontSize: '0.88rem', marginBottom: '12px' }}>
                    {b.cinemaName} • <strong>{b.screenName}</strong>
                  </div>

                  <div style={{ display: 'flex', flexWrap: 'wrap', gap: '18px', fontSize: '0.82rem', color: 'var(--text-dim)' }}>
                    <span>
                      <Calendar size={14} style={{ display: 'inline', marginRight: '4px' }} />
                      {formatDate(b.startTime)}
                    </span>
                    <span>
                      <Clock size={14} style={{ display: 'inline', marginRight: '4px' }} />
                      {formatTime(b.startTime)}
                    </span>
                    <span>
                      Seats ({b.seats.length}): <strong style={{ color: '#fff' }}>{b.seats.map((s) => `${s.rowLabel}${s.seatNumber}`).join(', ')}</strong>
                    </span>
                    <span>
                      Total: <strong style={{ color: '#10b981' }}>₹{b.totalAmount.toFixed(2)}</strong>
                    </span>
                  </div>
                </div>

                <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
                  <Link to={`/confirmation/${b.bookingReference}`} className="btn-secondary btn-sm">
                    View Ticket
                  </Link>

                  {isConfirmed && (
                    <button
                      onClick={() => setSelectedBookingForCancel(b)}
                      style={{
                        padding: '6px 12px',
                        borderRadius: 'var(--radius-md)',
                        fontSize: '0.82rem',
                        fontWeight: 600,
                        color: '#f87171',
                        border: '1px solid rgba(239, 68, 68, 0.4)',
                        background: 'rgba(239, 68, 68, 0.1)',
                      }}
                    >
                      Cancel Booking
                    </button>
                  )}
                </div>
              </div>
            );
          })}
        </div>
      )}

      {/* Cancellation Confirmation Modal */}
      {selectedBookingForCancel && (
        <div style={{
          position: 'fixed',
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          background: 'rgba(0, 0, 0, 0.75)',
          backdropFilter: 'blur(8px)',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          zIndex: 1000,
          padding: '20px'
        }}>
          <div className="glass-panel" style={{ maxWidth: '480px', width: '100%', padding: '32px', background: '#111624' }}>
            <h3 style={{ fontSize: '1.3rem', fontWeight: 800, marginBottom: '12px' }}>
              Confirm Booking Cancellation
            </h3>

            <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', lineHeight: 1.6, marginBottom: '18px' }}>
              Are you sure you want to cancel booking <strong>{selectedBookingForCancel.bookingReference}</strong> for <em>{selectedBookingForCancel.movieTitle}</em>?
            </p>

            <div style={{ background: 'rgba(239, 68, 68, 0.1)', border: '1px solid rgba(239, 68, 68, 0.3)', padding: '12px', borderRadius: '6px', fontSize: '0.82rem', color: '#fca5a5', marginBottom: '24px' }}>
              <strong>Cancellation Policy:</strong> A 100% refund of ₹{selectedBookingForCancel.totalAmount.toFixed(2)} will be issued to your payment method. Seats will be released back to the theater inventory immediately.
            </div>

            <div style={{ display: 'flex', gap: '12px', justifyContent: 'flex-end' }}>
              <button
                type="button"
                onClick={() => setSelectedBookingForCancel(null)}
                className="btn-secondary"
                disabled={cancelling}
              >
                Keep Booking
              </button>
              <button
                type="button"
                onClick={handleCancelBooking}
                disabled={cancelling}
                className="btn-crimson"
              >
                {cancelling ? 'Cancelling...' : 'Confirm & Refund'}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
