import React, { createContext, useContext, useEffect, useState } from 'react';
import { User } from '../types';
import { authApi } from '../api/authApi';

interface AuthContextType {
  user: User | null;
  token: string | null;
  loading: boolean;
  isAdmin: boolean;
  login: (email: string, password: string) => Promise<void>;
  register: (name: string, email: string, password: string) => Promise<void>;
  logout: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(() => {
    const saved = localStorage.getItem('seatsure_user');
    return saved ? JSON.parse(saved) : null;
  });
  const [token, setToken] = useState<string | null>(() => localStorage.getItem('seatsure_access_token'));
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const initAuth = async () => {
      const accessToken = localStorage.getItem('seatsure_access_token');
      if (accessToken) {
        try {
          const currentUser = await authApi.getCurrentUser();
          setUser(currentUser);
          localStorage.setItem('seatsure_user', JSON.stringify(currentUser));
        } catch {
          // Token invalid or expired, clear
          setUser(null);
          setToken(null);
          localStorage.removeItem('seatsure_access_token');
          localStorage.removeItem('seatsure_refresh_token');
          localStorage.removeItem('seatsure_user');
        }
      }
      setLoading(false);
    };

    initAuth();

    const handleLogoutEvent = () => {
      setUser(null);
      setToken(null);
    };

    window.addEventListener('seatsure_auth_logout', handleLogoutEvent);
    return () => window.removeEventListener('seatsure_auth_logout', handleLogoutEvent);
  }, []);

  const login = async (email: string, password: string) => {
    const res = await authApi.login({ email, password });
    localStorage.setItem('seatsure_access_token', res.accessToken);
    localStorage.setItem('seatsure_refresh_token', res.refreshToken);
    localStorage.setItem('seatsure_user', JSON.stringify(res.user));
    setToken(res.accessToken);
    setUser(res.user);
  };

  const register = async (name: string, email: string, password: string) => {
    const res = await authApi.register({ name, email, password });
    localStorage.setItem('seatsure_access_token', res.accessToken);
    localStorage.setItem('seatsure_refresh_token', res.refreshToken);
    localStorage.setItem('seatsure_user', JSON.stringify(res.user));
    setToken(res.accessToken);
    setUser(res.user);
  };

  const logout = async () => {
    const refreshToken = localStorage.getItem('seatsure_refresh_token') || undefined;
    await authApi.logout(refreshToken);
    localStorage.removeItem('seatsure_access_token');
    localStorage.removeItem('seatsure_refresh_token');
    localStorage.removeItem('seatsure_user');
    setUser(null);
    setToken(null);
  };

  const isAdmin = user?.role === 'ROLE_ADMIN';

  return (
    <AuthContext.Provider value={{ user, token, loading, isAdmin, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
