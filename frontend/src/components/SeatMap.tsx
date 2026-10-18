import React from 'react';
import { ShowSeat } from '../types';

interface SeatMapProps {
  seats: ShowSeat[];
  selectedSeatIds: number[];
  onToggleSeat: (seatId: number) => void;
  maxSelection?: number;
}

export const SeatMap: React.FC<SeatMapProps> = ({
  seats,
  selectedSeatIds,
  onToggleSeat,
  maxSelection: _maxSelection = 8,
}) => {
  // Group seats by row label
  const rowsMap = seats.reduce((acc, seat) => {
    if (!acc[seat.rowLabel]) {
      acc[seat.rowLabel] = [];
    }
    acc[seat.rowLabel].push(seat);
    return acc;
  }, {} as Record<string, ShowSeat[]>);

  const sortedRowLabels = Object.keys(rowsMap).sort();

  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', width: '100%', padding: '24px 0' }}>
      {/* Cinema Screen Projection Curve */}
      <div className="cinema-screen" />

      {/* Seating Grid */}
      <div style={{ display: 'flex', flexDirection: 'column', gap: '14px', width: '100%', maxWidth: '640px', margin: '0 auto 36px' }}>
        {sortedRowLabels.map((rowLabel) => {
          const rowSeats = rowsMap[rowLabel].sort((a, b) => a.seatNumber - b.seatNumber);
          const tier = rowSeats[0]?.seatType.toLowerCase() || 'silver';

          return (
            <div key={rowLabel} style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: '8px' }}>
              {/* Row Label Indicator */}
              <span style={{ width: '24px', textAlign: 'center', fontWeight: 700, fontSize: '0.85rem', color: 'var(--text-dim)' }}>
                {rowLabel}
              </span>

              {/* Seats in Row */}
              <div style={{ display: 'flex', gap: '8px' }}>
                {rowSeats.map((seat) => {
                  const isSelected = selectedSeatIds.includes(seat.seatId);
                  const isBooked = seat.status === 'BOOKED';
                  const isHeld = seat.status === 'HELD' && !seat.isHeldByCurrentUser;

                  let seatClass = `seat-node ${tier}`;
                  if (isSelected) {
                    seatClass += ' selected';
                  } else if (isBooked) {
                    seatClass += ' booked';
                  } else if (isHeld) {
                    seatClass += ' held';
                  } else {
                    seatClass += ' available';
                  }

                  return (
                    <button
                      key={seat.showSeatId}
                      className={seatClass}
                      disabled={isBooked || isHeld}
                      onClick={() => onToggleSeat(seat.seatId)}
                      title={`Row ${seat.rowLabel} Seat ${seat.seatNumber} (${seat.seatType}) - ₹${seat.price}`}
                    >
                      {seat.seatNumber}
                    </button>
                  );
                })}
              </div>

              {/* Right Row Label */}
              <span style={{ width: '24px', textAlign: 'center', fontWeight: 700, fontSize: '0.85rem', color: 'var(--text-dim)' }}>
                {rowLabel}
              </span>
            </div>
          );
        })}
      </div>

      {/* Legend & Tier Pricing Indicator */}
      <div style={{
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'center',
        alignItems: 'center',
        gap: '20px',
        padding: '16px 24px',
        borderRadius: 'var(--radius-md)',
        background: 'var(--bg-surface-elevated)',
        border: '1px solid var(--border-subtle)',
        fontSize: '0.82rem',
        color: 'var(--text-muted)'
      }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <div className="seat-node silver available" style={{ width: '18px', height: '18px', pointerEvents: 'none' }} />
          <span>Silver</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <div className="seat-node gold available" style={{ width: '18px', height: '18px', pointerEvents: 'none' }} />
          <span>Gold</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <div className="seat-node platinum available" style={{ width: '18px', height: '18px', pointerEvents: 'none' }} />
          <span>Platinum</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <div className="seat-node selected" style={{ width: '18px', height: '18px', pointerEvents: 'none' }} />
          <span>Selected</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <div className="seat-node held" style={{ width: '18px', height: '18px', pointerEvents: 'none' }} />
          <span>Held (Reserved)</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <div className="seat-node booked" style={{ width: '18px', height: '18px', pointerEvents: 'none' }} />
          <span>Booked</span>
        </div>
      </div>
    </div>
  );
};
