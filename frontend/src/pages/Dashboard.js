import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const modules = [
    { name: 'Patients', path: '/patients', icon: '👥', roles: ['ADMIN', 'DOCTOR', 'NURSE', 'RECEPTIONIST'] },
    { name: 'Outpatient', path: '/outpatient', icon: '🏥', roles: ['ADMIN', 'DOCTOR', 'NURSE', 'RECEPTIONIST'] },
    { name: 'Inpatient', path: '/inpatient', icon: '🛏️', roles: ['ADMIN', 'DOCTOR', 'NURSE'] },
    { name: 'Rooms', path: '/rooms', icon: '🚪', roles: ['ADMIN', 'NURSE'] },
    { name: 'Pharmacy', path: '/pharmacy', icon: '💊', roles: ['ADMIN', 'PHARMACIST', 'DOCTOR'] },
    { name: 'Lab Tests', path: '/lab', icon: '🔬', roles: ['ADMIN', 'DOCTOR', 'LAB_TECHNICIAN'] },
    { name: 'Billing', path: '/billing', icon: '💰', roles: ['ADMIN', 'RECEPTIONIST'] },
    { name: 'Users', path: '/users', icon: '👤', roles: ['ADMIN'] },
  ];

  const accessibleModules = modules.filter(module => 
    module.roles.includes(user?.role)
  );

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Hospital Management System</h1>
        <div className="user-info">
          <span>Welcome, {user?.fullName} ({user?.role})</span>
          <button onClick={handleLogout} className="btn-logout">Logout</button>
        </div>
      </div>
      
      <div className="dashboard-content">
        <h2>Modules</h2>
        <div className="modules-grid">
          {accessibleModules.map((module) => (
            <div
              key={module.name}
              className="module-card"
              onClick={() => navigate(module.path)}
            >
              <div className="module-icon">{module.icon}</div>
              <h3>{module.name}</h3>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
