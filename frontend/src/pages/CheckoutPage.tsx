import React, { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { holdApi } from '../api/holdApi';
import { bookingApi } from '../api/bookingApi';
import { paymentApi } from '../api/paymentApi';
import { SeatHoldResponse, BookingResponse } from '../types';
import { Clock, ShieldCheck, CreditCard, Smartphone, AlertTriangle } from 'lucide-react';

export const CheckoutPage: React.FC = () => {
  const [searchParams] = useSearchParams();
  const holdIdParam = searchParams.get('holdId');
  const navigate = useNavigate();

  const [hold, setHold] = useState<SeatHoldResponse | null>(null);
  const [remainingSeconds, setRemainingSeconds] = useState<number>(300);
  const [loading, setLoading] = useState<boolean>(true);
  const [paying, setPaying] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string | null>(null);

  // Payment form states
  const [paymentMethod, setPaymentMethod] = useState<'UPI' | 'CARD' | 'NETBANKING'>('UPI');
  const [simulationOutcome, setSimulationOutcome] = useState<'SUCCESS' | 'FAILURE' | 'TIMEOUT'>('SUCCESS');
  const [idempotencyKey] = useState<string>(() => `IDEMP_${Date.now()}_${Math.random().toString(36).substring(2, 9)}`);

  useEffect(() => {
    if (!holdIdParam) {
      navigate('/');
      return;
    }

    const fetchHold = async () => {
      try {
        const holdData = await holdApi.getHoldById(Number(holdIdParam));
        setHold(holdData);
        setRemainingSeconds(holdData.expiresInSeconds);
      } catch (err: any) {
        console.error('Failed to load seat hold', err);
        setErrorMsg('Seat hold expired or not found. Please re-select your seats.');
      } finally {
        setLoading(false);
      }
    };

    fetchHold();
  }, [holdIdParam, navigate]);

  // Live Countdown Timer
  useEffect(() => {
    if (remainingSeconds <= 0) return;

    const timer = setInterval(() => {
      setRemainingSeconds((prev) => {
        if (prev <= 1) {
          clearInterval(timer);
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, [remainingSeconds]);

  const formatTimer = (sec: number) => {
    const m = Math.floor(sec / 60);
    const s = sec % 60;
    return `${m}:${s < 10 ? '0' : ''}${s}`;
  };

  const handlePay = async () => {
    if (!hold) return;
    if (remainingSeconds <= 0) {
      setErrorMsg('Your seat reservation has expired. Please select seats again.');
      return;
    }

    setPaying(true);
    setErrorMsg(null);

    try {
      // 1. Convert hold into pending booking
      const booking: BookingResponse = await bookingApi.createBooking(hold.holdId);

      // 2. Submit simulated payment with idempotency key
      const paymentResponse = await paymentApi.processPayment(booking.id, {
        idempotencyKey,
        simulationOutcome,
        paymentMethod,
      });

      if (paymentResponse.status === 'SUCCESS') {
        navigate(`/confirmation/${booking.bookingReference}`);
      } else if (paymentResponse.status === 'INITIATED') {
        // Timeout scenario
        setErrorMsg('Payment request timed out at gateway. Order is pending reconciliation.');
      } else {
        // Failure scenario
        setErrorMsg(paymentResponse.failureReason || 'Payment declined by issuer. You can retry before hold expires.');
      }
    } catch (err: any) {
      const msg = err.response?.data?.message || 'Payment processing failed. Please try again.';
      setErrorMsg(msg);
    } finally {
      setPaying(false);
    }
  };

  if (loading) {
    return (
      <div style={{ maxWidth: '900px', margin: '0 auto', padding: '60px 24px', textAlign: 'center', color: 'var(--text-muted)' }}>
        Verifying seat reservation...
      </div>
    );
  }

  const isExpired = remainingSeconds <= 0;

  return (
    <div style={{ maxWidth: '960px', margin: '0 auto', padding: '24px' }}>
      {/* Expiration Timer Banner */}
      <div
        className={`glass-panel ${remainingSeconds < 60 && !isExpired ? 'countdown-pulse' : ''}`}
        style={{
          padding: '16px 24px',
          marginBottom: '32px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          background: isExpired ? 'rgba(239, 68, 68, 0.15)' : 'var(--bg-surface-elevated)',
          borderColor: isExpired ? '#ef4444' : remainingSeconds < 60 ? '#f59e0b' : 'var(--border-subtle)',
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
          <Clock size={22} color={isExpired ? '#ef4444' : remainingSeconds < 60 ? '#f59e0b' : '#38bdf8'} />
          <div>
            <div style={{ fontSize: '0.85rem', color: 'var(--text-dim)', fontWeight: 600 }}>
              {isExpired ? 'HOLD EXPIRED' : 'SEATS TEMPORARILY RESERVED'}
            </div>
            <div style={{ fontSize: '0.92rem' }}>
              {isExpired
                ? 'Your temporary reservation has expired and seats have been restored to inventory.'
                : 'Complete payment before the timer expires to secure your tickets.'}
            </div>
          </div>
        </div>

        <div style={{ fontSize: '1.8rem', fontWeight: 800, fontFamily: 'Outfit', color: isExpired ? '#ef4444' : remainingSeconds < 60 ? '#f59e0b' : '#38bdf8' }}>
          {isExpired ? '00:00' : formatTimer(remainingSeconds)}
        </div>
      </div>

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
          }}
        >
          <AlertTriangle size={20} color="#ef4444" />
          <span>{errorMsg}</span>
        </div>
      )}

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '28px' }}>
        {/* Left Column: Payment Simulator Options */}
        <div className="glass-panel" style={{ padding: '28px' }}>
          <h2 style={{ fontSize: '1.3rem', fontWeight: 800, marginBottom: '20px' }}>
            Simulated Payment Gateway
          </h2>

          {/* Payment Method Selector */}
          <div style={{ marginBottom: '24px' }}>
            <label style={{ display: 'block', fontSize: '0.85rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '10px' }}>
              PAYMENT METHOD
            </label>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
              <button
                type="button"
                onClick={() => setPaymentMethod('UPI')}
                style={{
                  padding: '12px 16px',
                  borderRadius: 'var(--radius-md)',
                  background: paymentMethod === 'UPI' ? 'rgba(99, 102, 241, 0.2)' : 'var(--bg-surface-elevated)',
                  border: '1px solid',
                  borderColor: paymentMethod === 'UPI' ? 'var(--accent-primary)' : 'var(--border-subtle)',
                  color: '#fff',
                  display: 'flex',
                  alignItems: 'center',
                  gap: '12px',
                  textAlign: 'left',
                }}
              >
                <Smartphone size={20} color="var(--accent-primary)" />
                <div>
                  <div style={{ fontWeight: 700, fontSize: '0.92rem' }}>UPI / Instant Pay</div>
                  <div style={{ fontSize: '0.75rem', color: 'var(--text-dim)' }}>Google Pay, PhonePe, Paytm</div>
                </div>
              </button>

              <button
                type="button"
                onClick={() => setPaymentMethod('CARD')}
                style={{
                  padding: '12px 16px',
                  borderRadius: 'var(--radius-md)',
                  background: paymentMethod === 'CARD' ? 'rgba(99, 102, 241, 0.2)' : 'var(--bg-surface-elevated)',
                  border: '1px solid',
                  borderColor: paymentMethod === 'CARD' ? 'var(--accent-primary)' : 'var(--border-subtle)',
                  color: '#fff',
                  display: 'flex',
                  alignItems: 'center',
                  gap: '12px',
                  textAlign: 'left',
                }}
              >
                <CreditCard size={20} color="var(--accent-primary)" />
                <div>
                  <div style={{ fontWeight: 700, fontSize: '0.92rem' }}>Credit / Debit Card</div>
                  <div style={{ fontSize: '0.75rem', color: 'var(--text-dim)' }}>Visa, MasterCard, RuPay</div>
                </div>
              </button>
            </div>
          </div>

          {/* Test Simulation Outcome Selector (Portfolio Feature!) */}
          <div style={{
            background: 'rgba(245, 158, 11, 0.1)',
            border: '1px solid rgba(245, 158, 11, 0.3)',
            padding: '16px',
            borderRadius: 'var(--radius-md)',
            marginBottom: '28px'
          }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '8px', color: '#fbbf24', fontSize: '0.85rem', fontWeight: 700 }}>
              <ShieldCheck size={16} /> Technical Interview Simulator
            </div>
            <p style={{ fontSize: '0.8rem', color: 'var(--text-muted)', marginBottom: '12px', lineHeight: 1.5 }}>
              Choose simulated provider response to verify transaction boundaries and inventory compensation:
            </p>

            <select
              value={simulationOutcome}
              onChange={(e) => setSimulationOutcome(e.target.value as any)}
              style={{
                width: '100%',
                padding: '10px 12px',
                borderRadius: 'var(--radius-md)',
                background: '#131826',
                border: '1px solid rgba(255,255,255,0.2)',
                color: '#fff',
                fontSize: '0.88rem',
                fontWeight: 600,
              }}
            >
              <option value="SUCCESS">SUCCESS (Booking Confirmed & Seats Booked)</option>
              <option value="FAILURE">FAILURE (Payment Declined & Seats Released)</option>
              <option value="TIMEOUT">TIMEOUT (Gateway Timeout & Pending State)</option>
            </select>
          </div>

          <button
            onClick={handlePay}
            disabled={isExpired || paying}
            className="btn-crimson"
            style={{ width: '100%', padding: '16px', fontSize: '1.05rem' }}
          >
            {paying ? 'Processing Simulated Payment...' : `Pay ₹${hold?.totalAmount.toFixed(2)}`}
          </button>
        </div>

        {/* Right Column: Order Summary */}
        <div className="glass-panel" style={{ padding: '28px', height: 'fit-content' }}>
          <h3 style={{ fontSize: '1.2rem', fontWeight: 800, marginBottom: '18px' }}>
            Order Summary
          </h3>

          <div style={{ borderBottom: '1px solid var(--border-subtle)', paddingBottom: '16px', marginBottom: '16px' }}>
            <div style={{ fontSize: '0.82rem', color: 'var(--text-dim)', marginBottom: '4px' }}>RESERVATION REFERENCE</div>
            <div style={{ fontWeight: 700, fontFamily: 'monospace', color: '#38bdf8' }}>HOLD #{hold?.holdId}</div>
          </div>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '12px', borderBottom: '1px solid var(--border-subtle)', paddingBottom: '16px', marginBottom: '16px', fontSize: '0.9rem' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span style={{ color: 'var(--text-muted)' }}>Ticket Quantity</span>
              <span style={{ fontWeight: 600 }}>{hold?.seatIds.length} Seats</span>
            </div>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span style={{ color: 'var(--text-muted)' }}>Convenience Fee</span>
              <span style={{ color: '#10b981', fontWeight: 600 }}>FREE</span>
            </div>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span style={{ color: 'var(--text-muted)' }}>Idempotency Guard</span>
              <span style={{ color: '#10b981', fontWeight: 600 }}>ACTIVE</span>
            </div>
          </div>

          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '24px' }}>
            <span style={{ fontSize: '1.1rem', fontWeight: 700 }}>Total Payable</span>
            <span style={{ fontSize: '1.5rem', fontWeight: 800, color: '#10b981' }}>
              ₹{hold?.totalAmount.toFixed(2)}
            </span>
          </div>

          <div style={{ fontSize: '0.78rem', color: 'var(--text-dim)', lineHeight: 1.5 }}>
            By clicking Pay, your payment will be simulated through the SeatSure transactional provider. In case of cancellation, eligible refunds are processed automatically.
          </div>
        </div>
      </div>
    </div>
  );
};
