import axios from '../utils/axios';
import { decodeToken } from '../utils/authUtils';

export const loginWithEmail = async (email, password) => {
    try {
        const response = await axios.post('/login', { email, password });
        const token = response.data.token;

        // Decode token to get user details
        const decodedToken = decodeToken(token);
        const role = decodedToken.role;

        // Store token and role in localStorage
        localStorage.setItem('token', token);
        localStorage.setItem('role', role);

        return token;
    } catch (error) {
        throw new Error('Login failed');
    }
};

export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    window.location.href = '/login'; // Redirect to login
};
