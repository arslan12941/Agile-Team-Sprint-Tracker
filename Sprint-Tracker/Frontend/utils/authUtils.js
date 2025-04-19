import jwt_decode from 'jwt-decode';

// Decode the JWT token and get user info (e.g., role)
export const decodeToken = (token) => {
    try {
        return jwt_decode(token);
    } catch (error) {
        return null;
    }
};

// Check if the token is expired
export const isTokenExpired = () => {
    const token = localStorage.getItem('token');
    if (!token) return true;

    const decodedToken = decodeToken(token);
    const currentTime = Date.now() / 1000;
    return decodedToken && decodedToken.exp < currentTime;
};

// Logout user and clear local storage
export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    window.location.href = '/login'; // Redirect to login
};
