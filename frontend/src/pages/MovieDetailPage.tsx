import React, { useEffect, useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { movieApi } from '../api/movieApi';
import { showApi } from '../api/showApi';
import { Movie, Show } from '../types';
import { useCity } from '../context/CityContext';
import { Clock, Calendar, Film, MapPin } from 'lucide-react';

export const MovieDetailPage: React.FC = () => {
  const { movieId } = useParams<{ movieId: string }>();
  const { selectedCity } = useCity();
  const [movie, setMovie] = useState<Movie | null>(null);
  const [shows, setShows] = useState<Show[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (!movieId) return;

    const fetchData = async () => {
      setLoading(true);
      try {
        const movieData = await movieApi.getMovieById(Number(movieId));
        setMovie(movieData);

        const showData = await showApi.getShowsForMovie(Number(movieId), selectedCity);
        setShows(showData);
      } catch (err) {
        console.error('Failed to load movie details or shows', err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [movieId, selectedCity]);

  // Group shows by Cinema
  const showsByCinema = shows.reduce((acc, show) => {
    if (!acc[show.cinemaName]) {
      acc[show.cinemaName] = {
        cinemaId: show.cinemaId,
        cinemaName: show.cinemaName,
        cinemaCity: show.cinemaCity,
        shows: [],
      };
    }
    acc[show.cinemaName].shows.push(show);
    return acc;
  }, {} as Record<string, { cinemaId: number; cinemaName: string; cinemaCity: string; shows: Show[] }>);

  const formatTime = (isoString: string) => {
    return new Date(isoString).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  const formatDate = (isoString: string) => {
    return new Date(isoString).toLocaleDateString([], { weekday: 'short', month: 'short', day: 'numeric' });
  };

  if (loading) {
    return (
      <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '60px 24px', textAlign: 'center', color: 'var(--text-muted)' }}>
        Loading movie details & showtimes...
      </div>
    );
  }

  if (!movie) {
    return (
      <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '60px 24px', textAlign: 'center' }}>
        <h2>Movie Not Found</h2>
        <Link to="/" className="btn-primary" style={{ marginTop: '20px' }}>
          Back to Movies
        </Link>
      </div>
    );
  }

  return (
    <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '24px' }}>
      {/* Movie Hero Header */}
      <div
        className="glass-panel"
        style={{
          display: 'flex',
          flexDirection: 'row',
          gap: '36px',
          padding: '36px',
          marginBottom: '48px',
          alignItems: 'center',
          flexWrap: 'wrap'
        }}
      >
        <img
          src={movie.posterUrl || 'https://images.unsplash.com/photo-1534447677768-be436bb09401?w=600'}
          alt={movie.title}
          style={{ width: '220px', height: '330px', objectFit: 'cover', borderRadius: 'var(--radius-md)', boxShadow: '0 10px 25px rgba(0,0,0,0.6)' }}
        />

        <div style={{ flex: 1, minWidth: '300px' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '12px' }}>
            <span className="badge badge-gold">{movie.contentRating}</span>
            <span className="badge badge-silver">{movie.language}</span>
            <span className="badge badge-platinum">{movie.genre}</span>
          </div>

          <h1 style={{ fontSize: '2.5rem', fontWeight: 800, marginBottom: '14px' }}>
            {movie.title}
          </h1>

          <div style={{ display: 'flex', alignItems: 'center', gap: '20px', color: 'var(--text-muted)', fontSize: '0.9rem', marginBottom: '20px' }}>
            <span style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
              <Clock size={16} /> {Math.floor(movie.durationMinutes / 60)}h {movie.durationMinutes % 60}m
            </span>
            <span style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
              <Calendar size={16} /> Release: {movie.releaseDate}
            </span>
          </div>

          <p style={{ color: '#cbd5e1', lineHeight: 1.7, fontSize: '0.95rem', marginBottom: '24px' }}>
            {movie.description}
          </p>

          <div style={{ display: 'flex', alignItems: 'center', gap: '8px', color: 'var(--text-dim)', fontSize: '0.85rem' }}>
            <MapPin size={16} color="var(--accent-primary)" />
            <span>Showing at multiple cinemas in <strong>{selectedCity}</strong></span>
          </div>
        </div>
      </div>

      {/* Showtimes Section */}
      <div>
        <h2 style={{ fontSize: '1.6rem', fontWeight: 800, marginBottom: '24px', display: 'flex', alignItems: 'center', gap: '10px' }}>
          <Film size={22} color="var(--accent-primary)" /> Select Cinema & Showtime in {selectedCity}
        </h2>

        {Object.keys(showsByCinema).length === 0 ? (
          <div className="glass-panel" style={{ padding: '48px 24px', textAlign: 'center' }}>
            <h3 style={{ fontSize: '1.2rem', marginBottom: '8px' }}>No Scheduled Screenings in {selectedCity}</h3>
            <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
              Currently no shows are scheduled for this movie in {selectedCity}. Please select another city from the top bar or check back soon.
            </p>
          </div>
        ) : (
          <div style={{ display: 'flex', flexDirection: 'column', gap: '24px' }}>
            {Object.values(showsByCinema).map((cinemaGroup) => (
              <div
                key={cinemaGroup.cinemaName}
                className="glass-panel"
                style={{ padding: '24px 28px' }}
              >
                <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '16px', borderBottom: '1px solid var(--border-subtle)', paddingBottom: '12px' }}>
                  <div>
                    <h3 style={{ fontSize: '1.2rem', fontWeight: 700 }}>{cinemaGroup.cinemaName}</h3>
                    <span style={{ fontSize: '0.82rem', color: 'var(--text-dim)' }}>{cinemaGroup.cinemaCity}</span>
                  </div>
                  <span className="badge badge-success">M-Ticket & F&B Available</span>
                </div>

                {/* Showtimes for this cinema */}
                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '14px' }}>
                  {cinemaGroup.shows.map((show) => (
                    <button
                      key={show.id}
                      onClick={() => navigate(`/shows/${show.id}/seats`)}
                      style={{
                        padding: '12px 18px',
                        borderRadius: 'var(--radius-md)',
                        background: 'var(--bg-surface-elevated)',
                        border: '1px solid var(--border-subtle)',
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        gap: '4px',
                        transition: 'all 0.2s ease',
                      }}
                      onMouseEnter={(e) => {
                        e.currentTarget.style.borderColor = 'var(--accent-primary)';
                        e.currentTarget.style.transform = 'translateY(-2px)';
                        e.currentTarget.style.boxShadow = '0 6px 16px rgba(99, 102, 241, 0.25)';
                      }}
                      onMouseLeave={(e) => {
                        e.currentTarget.style.borderColor = 'var(--border-subtle)';
                        e.currentTarget.style.transform = 'translateY(0)';
                        e.currentTarget.style.boxShadow = 'none';
                      }}
                    >
                      <span style={{ fontSize: '1.05rem', fontWeight: 700, color: '#38bdf8' }}>
                        {formatTime(show.startTime)}
                      </span>
                      <span style={{ fontSize: '0.72rem', color: 'var(--text-dim)' }}>
                        {formatDate(show.startTime)}
                      </span>
                      <span style={{ fontSize: '0.75rem', color: 'var(--text-muted)', fontWeight: 600 }}>
                        {show.screenName}
                      </span>
                      <span style={{ fontSize: '0.72rem', color: '#10b981', fontWeight: 700 }}>
                        From ₹{show.basePrice}
                      </span>
                    </button>
                  ))}
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};
