import React from 'react';
import { Link } from 'react-router-dom';
import { Movie } from '../types';
import { Clock, Ticket } from 'lucide-react';

interface MovieCardProps {
  movie: Movie;
}

export const MovieCard: React.FC<MovieCardProps> = ({ movie }) => {
  const formatDuration = (mins: number) => {
    const h = Math.floor(mins / 60);
    const m = mins % 60;
    return `${h}h ${m}m`;
  };

  return (
    <div
      className="glass-panel"
      style={{
        display: 'flex',
        flexDirection: 'column',
        overflow: 'hidden',
        transition: 'transform 0.25s ease, box-shadow 0.25s ease',
        height: '100%'
      }}
      onMouseEnter={(e) => {
        e.currentTarget.style.transform = 'translateY(-6px)';
        e.currentTarget.style.boxShadow = '0 12px 30px rgba(0, 0, 0, 0.6)';
        e.currentTarget.style.borderColor = 'rgba(99, 102, 241, 0.4)';
      }}
      onMouseLeave={(e) => {
        e.currentTarget.style.transform = 'translateY(0)';
        e.currentTarget.style.boxShadow = 'none';
        e.currentTarget.style.borderColor = 'var(--border-subtle)';
      }}
    >
      {/* Poster Image */}
      <div style={{ position: 'relative', width: '100%', height: '360px', overflow: 'hidden', backgroundColor: '#131722' }}>
        <img
          src={movie.posterUrl || 'https://images.unsplash.com/photo-1534447677768-be436bb09401?w=600'}
          alt={movie.title}
          style={{ width: '100%', height: '100%', objectFit: 'cover', transition: 'transform 0.4s ease' }}
          loading="lazy"
        />

        {/* Content Rating Badge */}
        <div style={{ position: 'absolute', top: '12px', right: '12px', background: 'rgba(0,0,0,0.75)', backdropFilter: 'blur(8px)', padding: '3px 8px', borderRadius: '4px', fontSize: '0.75rem', fontWeight: 700, border: '1px solid rgba(255,255,255,0.2)' }}>
          {movie.contentRating}
        </div>

        {/* Language Badge */}
        <div style={{ position: 'absolute', bottom: '12px', left: '12px', background: 'rgba(99, 102, 241, 0.85)', backdropFilter: 'blur(8px)', padding: '3px 8px', borderRadius: '4px', fontSize: '0.72rem', fontWeight: 700, color: '#fff' }}>
          {movie.language}
        </div>
      </div>

      {/* Content Body */}
      <div style={{ padding: '16px', display: 'flex', flexDirection: 'column', flex: 1, justifyContent: 'space-between' }}>
        <div>
          <h3 style={{ fontSize: '1.15rem', fontWeight: 700, marginBottom: '6px', whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>
            {movie.title}
          </h3>

          <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', color: 'var(--text-muted)', fontSize: '0.82rem', marginBottom: '14px' }}>
            <span>{movie.genre}</span>
            <span style={{ display: 'flex', alignItems: 'center', gap: '4px' }}>
              <Clock size={13} /> {formatDuration(movie.durationMinutes)}
            </span>
          </div>
        </div>

        <Link
          to={`/movies/${movie.id}`}
          className="btn-primary"
          style={{ width: '100%', textAlign: 'center', fontSize: '0.9rem' }}
        >
          <Ticket size={16} /> Book Tickets
        </Link>
      </div>
    </div>
  );
};
