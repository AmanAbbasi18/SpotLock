import React, { useEffect, useState } from 'react';
import { movieApi } from '../api/movieApi';
import { cinemaApi } from '../api/cinemaApi';
import { showApi } from '../api/showApi';
import { Movie, Cinema, Screen } from '../types';
import { Plus, ShieldCheck, Calendar, Activity } from 'lucide-react';

export const AdminDashboardPage: React.FC = () => {
  const [activeTab, setActiveTab] = useState<'MOVIES' | 'SHOWS' | 'CINEMAS'>('MOVIES');

  const [movies, setMovies] = useState<Movie[]>([]);
  const [cinemas, setCinemas] = useState<Cinema[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  // Add Movie Modal state
  const [showAddMovieModal, setShowAddMovieModal] = useState<boolean>(false);
  const [movieForm, setMovieForm] = useState(() => ({
    title: '',
    description: '',
    durationMinutes: 120,
    language: 'English',
    genre: 'Action',
    releaseDate: '2026-10-19',
    posterUrl: '',
    contentRating: 'UA',
  }));

  // Schedule Show Modal state
  const [showScheduleModal, setShowScheduleModal] = useState<boolean>(false);
  const [screensForCinema, setScreensForCinema] = useState<Screen[]>([]);
  const [selectedCinemaId, setSelectedCinemaId] = useState<number>(1);
  const [showForm, setShowForm] = useState({
    movieId: 1,
    screenId: 1,
    startTime: '',
    endTime: '',
    basePrice: 250,
  });

  const [statusMsg, setStatusMsg] = useState<{ text: string; type: 'success' | 'error' } | null>(null);

  const fetchData = async () => {
    setLoading(true);
    try {
      const [moviesRes, cinemasRes] = await Promise.all([
        movieApi.adminGetAllMovies({ size: 100 }),
        cinemaApi.getCinemas(),
      ]);
      setMovies(moviesRes.content);
      setCinemas(cinemasRes);

      if (cinemasRes.length > 0) {
        setSelectedCinemaId(cinemasRes[0].id);
        const screensRes = await cinemaApi.getScreensByCinema(cinemasRes[0].id);
        setScreensForCinema(screensRes);
      }
    } catch (err) {
      console.error('Failed to load admin dashboard data', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleCinemaChange = async (cinemaId: number) => {
    setSelectedCinemaId(cinemaId);
    try {
      const screensRes = await cinemaApi.getScreensByCinema(cinemaId);
      setScreensForCinema(screensRes);
      if (screensRes.length > 0) {
        setShowForm((prev) => ({ ...prev, screenId: screensRes[0].id }));
      }
    } catch (err) {
      console.error('Failed to load screens for cinema', err);
    }
  };

  const handleToggleMovieStatus = async (movie: Movie) => {
    const nextStatus = movie.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';
    try {
      await movieApi.adminUpdateStatus(movie.id, nextStatus);
      setStatusMsg({ text: `Movie '${movie.title}' status changed to ${nextStatus}`, type: 'success' });
      fetchData();
    } catch {
      setStatusMsg({ text: 'Failed to update movie status', type: 'error' });
    }
  };

  const handleCreateMovie = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await movieApi.adminCreateMovie(movieForm as any);
      setStatusMsg({ text: `Successfully created movie '${movieForm.title}'`, type: 'success' });
      setShowAddMovieModal(false);
      fetchData();
    } catch (err: any) {
      setStatusMsg({ text: err.response?.data?.message || 'Failed to create movie', type: 'error' });
    }
  };

  const handleScheduleShow = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await showApi.adminCreateShow({
        movieId: Number(showForm.movieId),
        screenId: Number(showForm.screenId),
        startTime: new Date(showForm.startTime).toISOString(),
        endTime: new Date(showForm.endTime).toISOString(),
        basePrice: Number(showForm.basePrice),
      });
      setStatusMsg({ text: 'Show scheduled successfully! Seat booking inventory generated.', type: 'success' });
      setShowScheduleModal(false);
    } catch (err: any) {
      setStatusMsg({ text: err.response?.data?.message || 'Conflict detected: Screen already has an overlapping show.', type: 'error' });
    }
  };

  return (
    <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '24px' }}>
      {/* Admin Header */}
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '32px', flexWrap: 'wrap', gap: '16px' }}>
        <div>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '6px' }}>
            <span className="badge badge-primary">
              <ShieldCheck size={14} /> Administrator Portal
            </span>
            <span className="badge badge-success">
              <Activity size={14} /> System Healthy
            </span>
          </div>
          <h1 style={{ fontSize: '2.2rem', fontWeight: 800 }}>Admin Operations Hub</h1>
        </div>

        <div style={{ display: 'flex', gap: '12px' }}>
          <button
            onClick={() => setShowAddMovieModal(true)}
            className="btn-primary"
          >
            <Plus size={16} /> Add Movie
          </button>
          <button
            onClick={() => setShowScheduleModal(true)}
            className="btn-crimson"
          >
            <Calendar size={16} /> Schedule Show
          </button>
        </div>
      </div>

      {statusMsg && (
        <div
          style={{
            background: statusMsg.type === 'success' ? 'rgba(16, 185, 129, 0.15)' : 'rgba(239, 68, 68, 0.15)',
            border: `1px solid ${statusMsg.type === 'success' ? '#10b981' : '#ef4444'}`,
            color: statusMsg.type === 'success' ? '#34d399' : '#f87171',
            padding: '14px 20px',
            borderRadius: 'var(--radius-md)',
            marginBottom: '28px',
          }}
        >
          {statusMsg.text}
        </div>
      )}

      {/* Tabs */}
      <div style={{ display: 'flex', gap: '8px', borderBottom: '1px solid var(--border-subtle)', paddingBottom: '12px', marginBottom: '28px' }}>
        {(['MOVIES', 'CINEMAS'] as const).map((tab) => (
          <button
            key={tab}
            onClick={() => setActiveTab(tab)}
            style={{
              padding: '8px 20px',
              borderRadius: 'var(--radius-md)',
              fontWeight: 700,
              fontSize: '0.9rem',
              background: activeTab === tab ? 'var(--bg-surface-elevated)' : 'transparent',
              color: activeTab === tab ? '#fff' : 'var(--text-muted)',
              border: '1px solid',
              borderColor: activeTab === tab ? 'var(--border-subtle)' : 'transparent',
            }}
          >
            {tab === 'MOVIES' ? 'Movie Catalog' : 'Cinemas & Screens'}
          </button>
        ))}
      </div>

      {loading && (
        <div style={{ textAlign: 'center', padding: '40px 20px', color: 'var(--text-muted)' }}>
          Loading dashboard inventory...
        </div>
      )}

      {/* Tab: Movies */}
      {!loading && activeTab === 'MOVIES' && (
        <div className="glass-panel" style={{ overflow: 'hidden' }}>
          <div style={{ padding: '20px 24px', borderBottom: '1px solid var(--border-subtle)', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <h3 style={{ fontSize: '1.2rem', fontWeight: 700 }}>Registered Movies ({movies.length})</h3>
          </div>

          <div style={{ overflowX: 'auto' }}>
            <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left', fontSize: '0.9rem' }}>
              <thead>
                <tr style={{ background: 'var(--bg-surface-elevated)', color: 'var(--text-dim)', borderBottom: '1px solid var(--border-subtle)' }}>
                  <th style={{ padding: '14px 24px' }}>Movie</th>
                  <th style={{ padding: '14px 24px' }}>Genre</th>
                  <th style={{ padding: '14px 24px' }}>Language</th>
                  <th style={{ padding: '14px 24px' }}>Duration</th>
                  <th style={{ padding: '14px 24px' }}>Rating</th>
                  <th style={{ padding: '14px 24px' }}>Status</th>
                  <th style={{ padding: '14px 24px', textAlign: 'right' }}>Actions</th>
                </tr>
              </thead>
              <tbody>
                {movies.map((m) => (
                  <tr key={m.id} style={{ borderBottom: '1px solid var(--border-subtle)' }}>
                    <td style={{ padding: '14px 24px', fontWeight: 600 }}>{m.title}</td>
                    <td style={{ padding: '14px 24px', color: 'var(--text-muted)' }}>{m.genre}</td>
                    <td style={{ padding: '14px 24px', color: 'var(--text-muted)' }}>{m.language}</td>
                    <td style={{ padding: '14px 24px', color: 'var(--text-muted)' }}>{m.durationMinutes} min</td>
                    <td style={{ padding: '14px 24px' }}>
                      <span className="badge badge-gold">{m.contentRating}</span>
                    </td>
                    <td style={{ padding: '14px 24px' }}>
                      <span className={`badge ${m.status === 'ACTIVE' ? 'badge-success' : 'badge-danger'}`}>
                        {m.status}
                      </span>
                    </td>
                    <td style={{ padding: '14px 24px', textAlign: 'right' }}>
                      <button
                        onClick={() => handleToggleMovieStatus(m)}
                        className="btn-secondary btn-sm"
                      >
                        {m.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}

      {/* Tab: Cinemas */}
      {!loading && activeTab === 'CINEMAS' && (
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '24px' }}>
          {cinemas.map((c) => (
            <div key={c.id} className="glass-panel" style={{ padding: '24px' }}>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '8px' }}>
                <span className="badge badge-primary">{c.city}</span>
                <span className="badge badge-success">{c.status}</span>
              </div>
              <h3 style={{ fontSize: '1.25rem', fontWeight: 800, marginBottom: '6px' }}>{c.name}</h3>
              <p style={{ color: 'var(--text-muted)', fontSize: '0.88rem', lineHeight: 1.5 }}>{c.address}</p>
            </div>
          ))}
        </div>
      )}

      {/* Add Movie Modal */}
      {showAddMovieModal && (
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
          <div className="glass-panel" style={{ maxWidth: '520px', width: '100%', padding: '32px', background: '#111624' }}>
            <h3 style={{ fontSize: '1.4rem', fontWeight: 800, marginBottom: '18px' }}>
              Add New Movie
            </h3>

            <form onSubmit={handleCreateMovie} style={{ display: 'flex', flexDirection: 'column', gap: '14px' }}>
              <div>
                <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>TITLE</label>
                <input
                  type="text"
                  required
                  value={movieForm.title}
                  onChange={(e) => setMovieForm({ ...movieForm, title: e.target.value })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                />
              </div>

              <div>
                <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>DESCRIPTION</label>
                <textarea
                  rows={3}
                  value={movieForm.description}
                  onChange={(e) => setMovieForm({ ...movieForm, description: e.target.value })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                />
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                <div>
                  <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>GENRE</label>
                  <input
                    type="text"
                    required
                    value={movieForm.genre}
                    onChange={(e) => setMovieForm({ ...movieForm, genre: e.target.value })}
                    style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                  />
                </div>
                <div>
                  <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>LANGUAGE</label>
                  <input
                    type="text"
                    required
                    value={movieForm.language}
                    onChange={(e) => setMovieForm({ ...movieForm, language: e.target.value })}
                    style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                  />
                </div>
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                <div>
                  <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>DURATION (MIN)</label>
                  <input
                    type="number"
                    required
                    min={1}
                    value={movieForm.durationMinutes}
                    onChange={(e) => setMovieForm({ ...movieForm, durationMinutes: Number(e.target.value) })}
                    style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                  />
                </div>
                <div>
                  <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>POSTER URL</label>
                  <input
                    type="text"
                    value={movieForm.posterUrl}
                    onChange={(e) => setMovieForm({ ...movieForm, posterUrl: e.target.value })}
                    style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                  />
                </div>
              </div>

              <div style={{ display: 'flex', gap: '12px', justifyContent: 'flex-end', marginTop: '16px' }}>
                <button
                  type="button"
                  onClick={() => setShowAddMovieModal(false)}
                  className="btn-secondary"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="btn-primary"
                >
                  Save Movie
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Schedule Show Modal */}
      {showScheduleModal && (
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
          <div className="glass-panel" style={{ maxWidth: '520px', width: '100%', padding: '32px', background: '#111624' }}>
            <h3 style={{ fontSize: '1.4rem', fontWeight: 800, marginBottom: '18px' }}>
              Schedule New Show
            </h3>

            <form onSubmit={handleScheduleShow} style={{ display: 'flex', flexDirection: 'column', gap: '14px' }}>
              <div>
                <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>SELECT MOVIE</label>
                <select
                  value={showForm.movieId}
                  onChange={(e) => setShowForm({ ...showForm, movieId: Number(e.target.value) })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                >
                  {movies.map((m) => (
                    <option key={m.id} value={m.id}>
                      {m.title} ({m.language})
                    </option>
                  ))}
                </select>
              </div>

              <div>
                <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>SELECT CINEMA</label>
                <select
                  value={selectedCinemaId}
                  onChange={(e) => handleCinemaChange(Number(e.target.value))}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                >
                  {cinemas.map((c) => (
                    <option key={c.id} value={c.id}>
                      {c.name} ({c.city})
                    </option>
                  ))}
                </select>
              </div>

              <div>
                <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>SCREEN</label>
                <select
                  value={showForm.screenId}
                  onChange={(e) => setShowForm({ ...showForm, screenId: Number(e.target.value) })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                >
                  {screensForCinema.map((s) => (
                    <option key={s.id} value={s.id}>
                      {s.name} ({s.totalSeats} seats)
                    </option>
                  ))}
                </select>
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
                <div>
                  <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>START TIME</label>
                  <input
                    type="datetime-local"
                    required
                    value={showForm.startTime}
                    onChange={(e) => setShowForm({ ...showForm, startTime: e.target.value })}
                    style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                  />
                </div>
                <div>
                  <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>END TIME</label>
                  <input
                    type="datetime-local"
                    required
                    value={showForm.endTime}
                    onChange={(e) => setShowForm({ ...showForm, endTime: e.target.value })}
                    style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                  />
                </div>
              </div>

              <div>
                <label style={{ display: 'block', fontSize: '0.82rem', color: 'var(--text-dim)', fontWeight: 600, marginBottom: '6px' }}>BASE PRICE (₹)</label>
                <input
                  type="number"
                  required
                  min={50}
                  value={showForm.basePrice}
                  onChange={(e) => setShowForm({ ...showForm, basePrice: Number(e.target.value) })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '6px', background: '#171c2c', border: '1px solid rgba(255,255,255,0.1)', color: '#fff' }}
                />
              </div>

              <div style={{ display: 'flex', gap: '12px', justifyContent: 'flex-end', marginTop: '16px' }}>
                <button
                  type="button"
                  onClick={() => setShowScheduleModal(false)}
                  className="btn-secondary"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="btn-crimson"
                >
                  Schedule Show
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};
