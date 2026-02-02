import api from './api';

export const authService = {
  login: async (credentials) => {
    const response = await api.post('/auth/login', credentials);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  },

  register: async (userData) => {
    const response = await api.post('/auth/register', userData);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  },

  logout: () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  },

  getCurrentUser: () => {
    try {
      const userStr = localStorage.getItem('user');
      return userStr ? JSON.parse(userStr) : null;
    } catch (error) {
      console.error('Error parsing user data:', error);
      return null;
    }
  },
};

export const patientService = {
  getAll: () => api.get('/patients'),
  getById: (id) => api.get(`/patients/${id}`),
  create: (data) => api.post('/patients', data),
  update: (id, data) => api.put(`/patients/${id}`, data),
  delete: (id) => api.delete(`/patients/${id}`),
  search: (name) => api.get(`/patients/search?name=${name}`),
};

export const outpatientService = {
  getAll: () => api.get('/outpatient'),
  getById: (id) => api.get(`/outpatient/${id}`),
  create: (data) => api.post('/outpatient', data),
  update: (id, data) => api.put(`/outpatient/${id}`, data),
  delete: (id) => api.delete(`/outpatient/${id}`),
  getByPatient: (patientId) => api.get(`/outpatient/patient/${patientId}`),
  getByDoctor: (doctorId) => api.get(`/outpatient/doctor/${doctorId}`),
  getByStatus: (status) => api.get(`/outpatient/status/${status}`),
};

export const inpatientService = {
  getAll: () => api.get('/inpatient'),
  getById: (id) => api.get(`/inpatient/${id}`),
  create: (data) => api.post('/inpatient', data),
  update: (id, data) => api.put(`/inpatient/${id}`, data),
  delete: (id) => api.delete(`/inpatient/${id}`),
  getActive: () => api.get('/inpatient/active'),
  discharge: (id, summary) => api.post(`/inpatient/${id}/discharge`, summary),
};

export const roomService = {
  getAll: () => api.get('/rooms'),
  getById: (id) => api.get(`/rooms/${id}`),
  create: (data) => api.post('/rooms', data),
  update: (id, data) => api.put(`/rooms/${id}`, data),
  delete: (id) => api.delete(`/rooms/${id}`),
  getAvailable: () => api.get('/rooms/available'),
  getByType: (type) => api.get(`/rooms/type/${type}`),
};

export const pharmacyService = {
  getAll: () => api.get('/pharmacy'),
  getById: (id) => api.get(`/pharmacy/${id}`),
  create: (data) => api.post('/pharmacy', data),
  update: (id, data) => api.put(`/pharmacy/${id}`, data),
  delete: (id) => api.delete(`/pharmacy/${id}`),
  getLowStock: () => api.get('/pharmacy/low-stock'),
};

export const labService = {
  getAll: () => api.get('/lab'),
  getById: (id) => api.get(`/lab/${id}`),
  create: (data) => api.post('/lab', data),
  update: (id, data) => api.put(`/lab/${id}`, data),
  delete: (id) => api.delete(`/lab/${id}`),
  getByPatient: (patientId) => api.get(`/lab/patient/${patientId}`),
  getByStatus: (status) => api.get(`/lab/status/${status}`),
};

export const billingService = {
  getAll: () => api.get('/billing'),
  getById: (id) => api.get(`/billing/${id}`),
  create: (data) => api.post('/billing', data),
  update: (id, data) => api.put(`/billing/${id}`, data),
  delete: (id) => api.delete(`/billing/${id}`),
  getByPatient: (patientId) => api.get(`/billing/patient/${patientId}`),
  getPending: () => api.get('/billing/pending'),
  makePayment: (id, amount) => api.post(`/billing/${id}/payment`, { amount }),
};

export const userService = {
  getAll: () => api.get('/users'),
  getById: (id) => api.get(`/users/${id}`),
  update: (id, data) => api.put(`/users/${id}`, data),
  delete: (id) => api.delete(`/users/${id}`),
  getByRole: (role) => api.get(`/users/role/${role}`),
};
