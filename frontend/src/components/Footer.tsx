import React from 'react';
import { Film, ShieldCheck, Database, Zap, Cpu } from 'lucide-react';

export const Footer: React.FC = () => {
  return (
    <footer style={{
      borderTop: '1px solid var(--border-subtle)',
      background: 'rgba(9, 11, 16, 0.95)',
      padding: '48px 24px 32px',
      marginTop: '80px'
    }}>
      <div style={{ maxWidth: '1280px', margin: '0 auto' }}>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))', gap: '40px', marginBottom: '40px' }}>
          {/* Brand Info */}
          <div>
            <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '16px' }}>
              <div style={{
                background: 'linear-gradient(135deg, #6366f1, #e50914)',
                padding: '6px',
                borderRadius: '8px',
                display: 'flex'
              }}>
                <Film size={18} color="#fff" />
              </div>
              <span style={{ fontSize: '1.25rem', fontWeight: 800 }}>SeatSure</span>
            </div>
            <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', lineHeight: 1.6 }}>
              Production-Grade Ticket Booking Platform inspired by BookMyShow.
              Built with rigorous concurrency protection, transactional outbox messaging, and cinematic dark mode aesthetics.
            </p>
          </div>

          {/* Architecture Highlights */}
          <div>
            <h4 style={{ fontSize: '0.95rem', fontWeight: 700, textTransform: 'uppercase', letterSpacing: '0.05em', color: 'var(--text-dim)', marginBottom: '16px' }}>
              Core Engineering
            </h4>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', fontSize: '0.85rem', color: 'var(--text-muted)' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                <ShieldCheck size={16} color="#10b981" />
                <span>Pessimistic Row-Locking Concurrency</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                <Database size={16} color="#6366f1" />
                <span>MySQL 8.4 LTS & Flyway Migrations</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                <Zap size={16} color="#f59e0b" />
                <span>Transactional Outbox & Kafka Events</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                <Cpu size={16} color="#06b6d4" />
                <span>Redis Accelerated Inventory Cache</span>
              </div>
            </div>
          </div>

          {/* Quick Links */}
          <div>
            <h4 style={{ fontSize: '0.95rem', fontWeight: 700, textTransform: 'uppercase', letterSpacing: '0.05em', color: 'var(--text-dim)', marginBottom: '16px' }}>
              Endpoints & Docs
            </h4>
            <ul style={{ listStyle: 'none', display: 'flex', flexDirection: 'column', gap: '8px', fontSize: '0.85rem', color: 'var(--text-muted)' }}>
              <li>
                <a href="/swagger-ui.html" target="_blank" rel="noreferrer" style={{ textDecoration: 'underline' }}>
                  Swagger OpenAPI 3.0 UI
                </a>
              </li>
              <li>
                <a href="/actuator/health" target="_blank" rel="noreferrer" style={{ textDecoration: 'underline' }}>
                  Actuator Health Endpoint
                </a>
              </li>
              <li>
                <a href="/v3/api-docs" target="_blank" rel="noreferrer" style={{ textDecoration: 'underline' }}>
                  JSON OpenAPI Specs
                </a>
              </li>
            </ul>
          </div>
        </div>

        <div style={{ borderTop: '1px solid var(--border-subtle)', paddingTop: '24px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '12px', fontSize: '0.8rem', color: 'var(--text-dim)' }}>
          <div>© 2026 SeatSure. All rights reserved. Designed for technical interviews and production portfolios.</div>
          <div>Built with Spring Boot 3 & React TypeScript.</div>
        </div>
      </div>
    </footer>
  );
};
