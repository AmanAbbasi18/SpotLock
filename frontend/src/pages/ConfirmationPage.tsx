import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { bookingApi } from '../api/bookingApi';
import { BookingResponse } from '../types';
import { CheckCircle, Printer, Ticket, Film, ShieldCheck } from 'lucide-react';

export const ConfirmationPage: React.FC = () => {
  const { reference } = useParams<{ reference: string }>();
  const [booking, setBooking] = useState<BookingResponse | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    if (!reference) return;

    const fetchBooking = async () => {
      try {
        const data = await bookingApi.getBookingByReference(reference);
        setBooking(data);
      } catch (err) {
        console.error('Failed to load confirmed booking', err);
      } finally {
        setLoading(false);
      }
    };

    fetchBooking();
  }, [reference]);

  if (loading) {
    return (
      <div style={{ maxWidth: '800px', margin: '0 auto', padding: '60px 24px', textAlign: 'center', color: 'var(--text-muted)' }}>
        Retrieving your confirmed ticket...
      </div>
    );
  }

  if (!booking) {
    return (
      <div style={{ maxWidth: '800px', margin: '0 auto', padding: '60px 24px', textAlign: 'center' }}>
        <h2>Booking Not Found</h2>
        <Link to="/" className="btn-primary" style={{ marginTop: '20px' }}>
          Back to Movies
        </Link>
      </div>
    );
  }

  const formatTime = (isoString: string) => {
    return new Date(isoString).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  const formatDate = (isoString: string) => {
    return new Date(isoString).toLocaleDateString([], { weekday: 'long', year: 'numeric', month: 'short', day: 'numeric' });
  };

  return (
    <div style={{ maxWidth: '780px', margin: '0 auto', padding: '32px 24px' }}>
      {/* Success Hero Header */}
      <div style={{ textAlign: 'center', marginBottom: '32px' }}>
        <div style={{
          width: '64px',
          height: '64px',
          borderRadius: '50%',
          background: 'rgba(16, 185, 129, 0.2)',
          border: '2px solid #10b981',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          margin: '0 auto 16px',
          boxShadow: '0 0 24px rgba(16, 185, 129, 0.4)'
        }}>
          <CheckCircle size={36} color="#10b981" />
        </div>
        <h1 style={{ fontSize: '2.2rem', fontWeight: 800, marginBottom: '8px' }}>
          Booking Confirmed!
        </h1>
        <p style={{ color: 'var(--text-muted)', fontSize: '0.95rem' }}>
          Your payment was processed successfully and seats have been allocated.
        </p>
      </div>

      {/* Ticket Card */}
      <div
        className="glass-panel"
        style={{
          overflow: 'hidden',
          border: '1px solid rgba(255, 255, 255, 0.15)',
          boxShadow: '0 20px 40px rgba(0, 0, 0, 0.6)',
          marginBottom: '32px'
        }}
      >
        {/* Ticket Header */}
        <div style={{
          background: 'linear-gradient(135deg, #4f46e5 0%, #1e1b4b 100%)',
          padding: '24px 32px',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          borderBottom: '1px dashed rgba(255, 255, 255, 0.2)'
        }}>
          <div>
            <div style={{ fontSize: '0.8rem', letterSpacing: '0.1em', textTransform: 'uppercase', color: '#a5b4fc', fontWeight: 700 }}>
              SEATSURE M-TICKET
            </div>
            <div style={{ fontSize: '1.4rem', fontWeight: 800, color: '#fff', fontFamily: 'monospace' }}>
              {booking.bookingReference}
            </div>
          </div>
          <div className="badge badge-success" style={{ fontSize: '0.85rem', padding: '6px 12px' }}>
            <ShieldCheck size={14} /> CONFIRMED
          </div>
        </div>

        {/* Ticket Body */}
        <div style={{ padding: '32px', display: 'flex', gap: '28px', flexWrap: 'wrap' }}>
          <img
            src={booking.moviePosterUrl || 'https://images.unsplash.com/photo-1534447677768-be436bb09401?w=400'}
            alt={booking.movieTitle}
            style={{ width: '130px', height: '195px', objectFit: 'cover', borderRadius: 'var(--radius-md)' }}
          />

          <div style={{ flex: 1, minWidth: '240px' }}>
            <h2 style={{ fontSize: '1.6rem', fontWeight: 800, marginBottom: '8px' }}>
              {booking.movieTitle}
            </h2>

            <div style={{ color: 'var(--text-muted)', fontSize: '0.92rem', marginBottom: '16px' }}>
              <strong>{booking.cinemaName}</strong> • {booking.screenName}
            </div>

            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(140px, 1fr))', gap: '14px', marginBottom: '20px' }}>
              <div>
                <div style={{ fontSize: '0.75rem', color: 'var(--text-dim)', fontWeight: 600 }}>SHOW DATE</div>
                <div style={{ fontSize: '0.9rem', fontWeight: 600 }}>{formatDate(booking.startTime)}</div>
              </div>
              <div>
                <div style={{ fontSize: '0.75rem', color: 'var(--text-dim)', fontWeight: 600 }}>TIME</div>
                <div style={{ fontSize: '0.9rem', fontWeight: 600, color: '#38bdf8' }}>{formatTime(booking.startTime)}</div>
              </div>
              <div>
                <div style={{ fontSize: '0.75rem', color: 'var(--text-dim)', fontWeight: 600 }}>SEATS ({booking.seats.length})</div>
                <div style={{ fontSize: '0.95rem', fontWeight: 700, color: '#10b981' }}>
                  {booking.seats.map((s) => `${s.rowLabel}${s.seatNumber}`).join(', ')}
                </div>
              </div>
              <div>
                <div style={{ fontSize: '0.75rem', color: 'var(--text-dim)', fontWeight: 600 }}>TOTAL AMOUNT</div>
                <div style={{ fontSize: '1.1rem', fontWeight: 800, color: '#fff' }}>₹{booking.totalAmount.toFixed(2)}</div>
              </div>
            </div>

            {/* Simulated Digital Barcode */}
            <div style={{
              background: '#000',
              padding: '12px 18px',
              borderRadius: '6px',
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              gap: '4px',
              border: '1px solid rgba(255,255,255,0.1)'
            }}>
              <div style={{ letterSpacing: '6px', fontFamily: 'monospace', fontWeight: 700, fontSize: '0.85rem', color: '#94a3b8' }}>
                ||||| | |||| ||| |||| | ||||| |||||
              </div>
              <div style={{ fontSize: '0.7rem', color: 'var(--text-dim)', letterSpacing: '0.05em' }}>
                SCAN AT AUDITORIUM ENTRANCE
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Action Buttons */}
      <div style={{ display: 'flex', gap: '16px', justifyContent: 'center', flexWrap: 'wrap' }}>
        <button
          onClick={() => window.print()}
          className="btn-secondary"
        >
          <Printer size={16} /> Print Ticket
        </button>
        <Link to="/my-bookings" className="btn-primary">
          <Ticket size={16} /> View My Bookings
        </Link>
        <Link to="/" className="btn-secondary">
          <Film size={16} /> Browse More Movies
        </Link>
      </div>
    </div>
  );
};
