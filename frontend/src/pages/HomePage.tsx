import React, { useEffect, useState } from 'react';
import { movieApi } from '../api/movieApi';
import { Movie } from '../types';
import { useCity } from '../context/CityContext';
import { MovieCard } from '../components/MovieCard';
import { Search, Sparkles, Film } from 'lucide-react';

export const HomePage: React.FC = () => {
  const { selectedCity } = useCity();
  const [movies, setMovies] = useState<Movie[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [search, setSearch] = useState<string>('');
  const [selectedGenre, setSelectedGenre] = useState<string>('All');
  const [selectedLanguage, setSelectedLanguage] = useState<string>('All');

  const genres = ['All', 'Sci-Fi', 'Action', 'Drama', 'Mythological'];
  const languages = ['All', 'English', 'Hindi'];

  useEffect(() => {
    const fetchMovies = async () => {
      setLoading(true);
      try {
        const res = await movieApi.getMovies({
          city: selectedCity,
          genre: selectedGenre !== 'All' ? selectedGenre : undefined,
          language: selectedLanguage !== 'All' ? selectedLanguage : undefined,
          search: search.trim() ? search.trim() : undefined,
        });
        setMovies(res.content);
      } catch (err) {
        console.error('Failed to load movies', err);
      } finally {
        setLoading(false);
      }
    };

    fetchMovies();
  }, [selectedCity, selectedGenre, selectedLanguage, search]);

  const featuredMovie = movies[0];

  return (
    <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '24px' }}>
      {/* Hero Showcase Banner */}
      {featuredMovie && (
        <div
          className="glass-panel"
          style={{
            position: 'relative',
            borderRadius: 'var(--radius-lg)',
            overflow: 'hidden',
            marginBottom: '48px',
            minHeight: '400px',
            display: 'flex',
            alignItems: 'flex-end',
            boxShadow: '0 20px 40px rgba(0, 0, 0, 0.7)'
          }}
        >
          {/* Background Blurred Banner Image */}
          <div
            style={{
              position: 'absolute',
              top: 0,
              left: 0,
              right: 0,
              bottom: 0,
              backgroundImage: `url(${featuredMovie.posterUrl})`,
              backgroundSize: 'cover',
              backgroundPosition: 'center 20%',
              filter: 'brightness(0.35)',
              transform: 'scale(1.05)'
            }}
          />

          {/* Hero Content Overlay */}
          <div style={{ position: 'relative', zIndex: 1, padding: '40px', maxWidth: '700px' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '12px' }}>
              <span className="badge badge-gold">
                <Sparkles size={13} /> Featured in {selectedCity}
              </span>
              <span className="badge badge-silver">{featuredMovie.language}</span>
            </div>

            <h1 style={{ fontSize: '2.8rem', fontWeight: 800, marginBottom: '14px', textShadow: '0 2px 10px rgba(0,0,0,0.8)' }}>
              {featuredMovie.title}
            </h1>

            <p style={{ color: '#cbd5e1', fontSize: '1rem', lineHeight: 1.6, marginBottom: '24px', textShadow: '0 1px 4px rgba(0,0,0,0.8)' }}>
              {featuredMovie.description}
            </p>

            <div style={{ display: 'flex', gap: '14px' }}>
              <a href={`/movies/${featuredMovie.id}`} className="btn-crimson">
                <Film size={18} /> Book Now
              </a>
              <a href={`/movies/${featuredMovie.id}`} className="btn-secondary">
                View Showtimes
              </a>
            </div>
          </div>
        </div>
      )}

      {/* Filter and Search Bar */}
      <div
        className="glass-panel"
        style={{
          padding: '20px 24px',
          marginBottom: '36px',
          display: 'flex',
          flexWrap: 'wrap',
          gap: '20px',
          alignItems: 'center',
          justifyContent: 'space-between'
        }}
      >
        {/* Search Input */}
        <div style={{
          display: 'flex',
          alignItems: 'center',
          gap: '10px',
          background: 'var(--bg-surface-elevated)',
          padding: '10px 16px',
          borderRadius: 'var(--radius-md)',
          border: '1px solid var(--border-subtle)',
          minWidth: '280px',
          flex: 1
        }}>
          <Search size={18} color="var(--text-dim)" />
          <input
            type="text"
            placeholder="Search movies by title..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            style={{ background: 'transparent', border: 'none', color: '#fff', width: '100%', fontSize: '0.9rem' }}
          />
        </div>

        {/* Genre Filter Chips */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px', flexWrap: 'wrap' }}>
          <span style={{ fontSize: '0.85rem', color: 'var(--text-dim)', fontWeight: 600 }}>Genre:</span>
          {genres.map((g) => (
            <button
              key={g}
              onClick={() => setSelectedGenre(g)}
              style={{
                padding: '6px 14px',
                borderRadius: 'var(--radius-full)',
                fontSize: '0.82rem',
                fontWeight: 600,
                background: selectedGenre === g ? 'var(--accent-primary)' : 'var(--bg-surface-elevated)',
                color: selectedGenre === g ? '#fff' : 'var(--text-muted)',
                border: '1px solid',
                borderColor: selectedGenre === g ? 'var(--accent-primary)' : 'var(--border-subtle)'
              }}
            >
              {g}
            </button>
          ))}
        </div>

        {/* Language Filter Chips */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
          <span style={{ fontSize: '0.85rem', color: 'var(--text-dim)', fontWeight: 600 }}>Language:</span>
          {languages.map((l) => (
            <button
              key={l}
              onClick={() => setSelectedLanguage(l)}
              style={{
                padding: '6px 14px',
                borderRadius: 'var(--radius-full)',
                fontSize: '0.82rem',
                fontWeight: 600,
                background: selectedLanguage === l ? 'var(--accent-crimson)' : 'var(--bg-surface-elevated)',
                color: selectedLanguage === l ? '#fff' : 'var(--text-muted)',
                border: '1px solid',
                borderColor: selectedLanguage === l ? 'var(--accent-crimson)' : 'var(--border-subtle)'
              }}
            >
              {l}
            </button>
          ))}
        </div>
      </div>

      {/* Movies Grid Section */}
      <div style={{ marginBottom: '40px' }}>
        <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '24px' }}>
          <h2 style={{ fontSize: '1.75rem', fontWeight: 800 }}>
            Now Showing in <span style={{ color: 'var(--accent-primary)' }}>{selectedCity}</span>
          </h2>
          <span style={{ color: 'var(--text-dim)', fontSize: '0.9rem' }}>
            {movies.length} {movies.length === 1 ? 'Movie' : 'Movies'} Available
          </span>
        </div>

        {loading ? (
          <div style={{ textAlign: 'center', padding: '60px 0', color: 'var(--text-muted)' }}>
            Loading movies in {selectedCity}...
          </div>
        ) : movies.length === 0 ? (
          <div className="glass-panel" style={{ textAlign: 'center', padding: '60px 20px' }}>
            <Film size={40} color="var(--text-dim)" style={{ marginBottom: '16px' }} />
            <h3 style={{ fontSize: '1.2rem', marginBottom: '8px' }}>No Movies Found</h3>
            <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
              No active screenings matched your search or filters in {selectedCity}. Try selecting another city or clearing filters.
            </p>
          </div>
        ) : (
          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(auto-fill, minmax(260px, 1fr))',
            gap: '24px'
          }}>
            {movies.map((movie) => (
              <MovieCard key={movie.id} movie={movie} />
            ))}
          </div>
        )}
      </div>
    </div>
  );
};
