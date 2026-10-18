import React, { useEffect, useState, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { showApi } from '../api/showApi';
import { holdApi } from '../api/holdApi';
import { Show, ShowSeat } from '../types';
import { SeatMap } from '../components/SeatMap';
import { useAuth } from '../context/AuthContext';
import { AlertCircle, ArrowRight, RefreshCw } from 'lucide-react';

export const SeatSelectionPage: React.FC = () => {
  const { showId } = useParams<{ showId: string }>();
  const { user } = useAuth();
  const navigate = useNavigate();

  const [show, setShow] = useState<Show | null>(null);
  const [seats, setSeats] = useState<ShowSeat[]>([]);
  const [selectedSeatIds, setSelectedSeatIds] = useState<number[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [holding, setHolding] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string | null>(null);

  const fetchSeatData = useCallback(async () => {
    if (!showId) return;
    try {
      const showData = await showApi.getShowById(Number(showId));
      setShow(showData);

      const seatAvailability = await showApi.getSeatAvailability(Number(showId));
      setSeats(seatAvailability.seats);

      // Pre-select any seats currently held by this user
      const myHeldSeats = seatAvailability.seats
        .filter((s) => s.isHeldByCurrentUser)
        .map((s) => s.seatId);
      if (myHeldSeats.length > 0) {
        setSelectedSeatIds((prev) => (prev.length === 0 ? myHeldSeats : prev));
      }
    } catch (err) {
      console.error('Failed to load show seat availability', err);
      setErrorMsg('Could not load seats. Please try refreshing.');
    } finally {
      setLoading(false);
    }
  }, [showId]);

  useEffect(() => {
    fetchSeatData();
    const interval = setInterval(fetchSeatData, 15000); // Poll availability every 15s
    return () => clearInterval(interval);
  }, [fetchSeatData]);

  const handleToggleSeat = (seatId: number) => {
    setErrorMsg(null);
    if (selectedSeatIds.includes(seatId)) {
      setSelectedSeatIds((prev) => prev.filter((id) => id !== seatId));
    } else {
      if (selectedSeatIds.length >= 8) {
        setErrorMsg('Maximum 8 seats can be selected per booking');
        return;
      }
      setSelectedSeatIds((prev) => [...prev, seatId]);
    }
  };

  const selectedSeatsInfo = seats.filter((s) => selectedSeatIds.includes(s.seatId));
  const subtotal = selectedSeatsInfo.reduce((acc, s) => acc + s.price, 0);

  const handleProceedToHold = async () => {
    if (!user) {
      navigate('/login', { state: { from: `/shows/${showId}/seats` } });
      return;
    }

    if (selectedSeatIds.length === 0) {
      setErrorMsg('Please select at least one seat to continue');
      return;
    }

    setHolding(true);
    setErrorMsg(null);

    try {
      const holdResponse = await holdApi.createHold(Number(showId), selectedSeatIds);
      // Navigate directly to Checkout with acquired Hold ID
      navigate(`/checkout?holdId=${holdResponse.holdId}`);
    } catch (err: any) {
      const serverMessage = err.response?.data?.message || 'Selected seat(s) are no longer available. Please select other seats.';
      setErrorMsg(serverMessage);
      // Refresh seat layout to reflect newly held/booked seats
      fetchSeatData();
    } finally {
      setHolding(false);
    }
  };

  if (loading) {
    return (
      <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '60px 24px', textAlign: 'center', color: 'var(--text-muted)' }}>
        Loading seat layout...
      </div>
    );
  }

  if (!show) {
    return (
      <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '60px 24px', textAlign: 'center' }}>
        <h2>Show not found</h2>
      </div>
    );
  }

  const formatTime = (isoString: string) => {
    return new Date(isoString).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  return (
    <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '24px' }}>
      {/* Top Show Info Bar */}
      <div
        className="glass-panel"
        style={{
          padding: '18px 28px',
          marginBottom: '24px',
          display: 'flex',
          flexWrap: 'wrap',
          alignItems: 'center',
          justifyContent: 'space-between',
          gap: '16px'
        }}
      >
        <div>
          <h2 style={{ fontSize: '1.4rem', fontWeight: 800, marginBottom: '4px' }}>
            {show.movieTitle}
          </h2>
          <div style={{ color: 'var(--text-muted)', fontSize: '0.88rem' }}>
            {show.cinemaName} • <strong>{show.screenName}</strong> • {formatTime(show.startTime)}
          </div>
        </div>

        <button
          onClick={fetchSeatData}
          className="btn-secondary btn-sm"
          title="Refresh seat availability"
        >
          <RefreshCw size={14} /> Refresh Seats
        </button>
      </div>

      {/* Error / Conflict Alert */}
      {errorMsg && (
        <div
          style={{
            background: 'rgba(239, 68, 68, 0.15)',
            border: '1px solid #ef4444',
            color: '#fca5a5',
            padding: '14px 20px',
            borderRadius: 'var(--radius-md)',
            marginBottom: '24px',
            display: 'flex',
            alignItems: 'center',
            gap: '12px',
            fontSize: '0.92rem'
          }}
        >
          <AlertCircle size={20} color="#ef4444" />
          <span>{errorMsg}</span>
        </div>
      )}

      {/* Interactive Seat Map */}
      <div className="glass-panel" style={{ padding: '32px 24px', marginBottom: '24px' }}>
        <SeatMap
          seats={seats}
          selectedSeatIds={selectedSeatIds}
          onToggleSeat={handleToggleSeat}
        />
      </div>

      {/* Bottom Sticky Action / Pricing Bar */}
      <div
        className="glass-panel"
        style={{
          position: 'sticky',
          bottom: '20px',
          padding: '18px 28px',
          display: 'flex',
          flexWrap: 'wrap',
          alignItems: 'center',
          justifyContent: 'space-between',
          gap: '20px',
          boxShadow: '0 10px 30px rgba(0, 0, 0, 0.8)',
          borderColor: selectedSeatIds.length > 0 ? 'var(--accent-primary)' : 'var(--border-subtle)'
        }}
      >
        <div>
          <div style={{ fontSize: '0.82rem', color: 'var(--text-dim)', marginBottom: '4px' }}>
            SELECTED SEATS ({selectedSeatIds.length})
          </div>
          <div style={{ fontSize: '1.1rem', fontWeight: 700 }}>
            {selectedSeatsInfo.length > 0
              ? selectedSeatsInfo.map((s) => `${s.rowLabel}${s.seatNumber}`).join(', ')
              : 'None selected'}
          </div>
        </div>

        <div style={{ display: 'flex', alignItems: 'center', gap: '28px' }}>
          <div>
            <div style={{ fontSize: '0.82rem', color: 'var(--text-dim)', marginBottom: '4px' }}>
              SUBTOTAL
            </div>
            <div style={{ fontSize: '1.4rem', fontWeight: 800, color: '#10b981' }}>
              ₹{subtotal.toFixed(2)}
            </div>
          </div>

          <button
            onClick={handleProceedToHold}
            disabled={selectedSeatIds.length === 0 || holding}
            className="btn-primary"
            style={{ padding: '14px 28px', fontSize: '1rem' }}
          >
            {holding ? 'Locking Seats...' : (
              <>
                Hold & Proceed <ArrowRight size={18} />
              </>
            )}
          </button>
        </div>
      </div>
    </div>
  );
};
