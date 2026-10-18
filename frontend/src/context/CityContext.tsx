import React, { createContext, useContext, useEffect, useState } from 'react';
import { cinemaApi } from '../api/cinemaApi';

interface CityContextType {
  cities: string[];
  selectedCity: string;
  setSelectedCity: (city: string) => void;
  loading: boolean;
}

const CityContext = createContext<CityContextType | undefined>(undefined);

export const CityProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [cities, setCities] = useState<string[]>(['Mumbai', 'Bengaluru', 'Delhi NCR', 'Hyderabad']);
  const [selectedCity, setSelectedCityState] = useState<string>(() => {
    return localStorage.getItem('seatsure_city') || 'Mumbai';
  });
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const fetchedCities = await cinemaApi.getCities();
        if (fetchedCities && fetchedCities.length > 0) {
          setCities(fetchedCities);
          if (!fetchedCities.includes(selectedCity)) {
            setSelectedCityState(fetchedCities[0]);
            localStorage.setItem('seatsure_city', fetchedCities[0]);
          }
        }
      } catch (err) {
        console.warn('Could not fetch cities, using defaults', err);
      } finally {
        setLoading(false);
      }
    };

    fetchCities();
  }, [selectedCity]);

  const setSelectedCity = (city: string) => {
    setSelectedCityState(city);
    localStorage.setItem('seatsure_city', city);
  };

  return (
    <CityContext.Provider value={{ cities, selectedCity, setSelectedCity, loading }}>
      {children}
    </CityContext.Provider>
  );
};

export const useCity = () => {
  const context = useContext(CityContext);
  if (!context) {
    throw new Error('useCity must be used within a CityProvider');
  }
  return context;
};
