import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import Patients from './pages/Patients';
import './App.css';

const PrivateRoute = ({ children }) => {
  const { isAuthenticated, loading } = useAuth();
  
  if (loading) {
    return <div className="loading">Loading...</div>;
  }
  
  return isAuthenticated ? children : <Navigate to="/login" />;
};

function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route
        path="/dashboard"
        element={
          <PrivateRoute>
            <Dashboard />
          </PrivateRoute>
        }
      />
      <Route
        path="/patients"
        element={
          <PrivateRoute>
            <Patients />
          </PrivateRoute>
        }
      />
      <Route
        path="/outpatient"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>Outpatient Module</h2>
              <p>Manage outpatient visits and appointments</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route
        path="/inpatient"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>Inpatient Module</h2>
              <p>Manage inpatient admissions and discharges</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route
        path="/rooms"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>Room Management</h2>
              <p>Manage hospital rooms and allocations</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route
        path="/pharmacy"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>Pharmacy Module</h2>
              <p>Manage pharmacy stock and medications</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route
        path="/lab"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>Lab Tests Module</h2>
              <p>Manage laboratory tests and results</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route
        path="/billing"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>Billing Module</h2>
              <p>Manage patient billing and payments</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route
        path="/users"
        element={
          <PrivateRoute>
            <div className="module-placeholder">
              <h2>User Management</h2>
              <p>Manage system users and roles</p>
            </div>
          </PrivateRoute>
        }
      />
      <Route path="/" element={<Navigate to="/dashboard" />} />
    </Routes>
  );
}

function App() {
  return (
    <Router>
      <AuthProvider>
        <AppRoutes />
      </AuthProvider>
    </Router>
  );
}

export default App;
