import axios from 'axios';

// Create an Axios instance with default settings
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // Update with your backend API base URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add JWT token to the request header
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
export const setAuthToken = (token) => {
    if (token) {
        axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    } else {
        delete axiosInstance.defaults.headers.common['Authorization'];
    }
};
export const loginWithEmail = async (email, password) => {
    try {
        const response = await axiosInstance.post('/login', { email, password });
        const token = response.data.token;  // Get the JWT token from the response

        // Save the token in localStorage
        localStorage.setItem('token', token);

        return token;
    } catch (error) {
        throw new Error('Login failed');
    }
};