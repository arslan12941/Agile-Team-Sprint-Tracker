import React, { useState } from 'react';
import { loginWithEmail } from '../Services/authservice';
import { useHistory } from 'react-router-dom';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useHistory();

    const handleLogin = async () => {
        try {
            await loginWithEmail(email, password);
            const role = localStorage.getItem('role');
            if (role === 'ADMIN') {
                history.push('/admin-dashboard');
            } else {
                history.push('/user-dashboard');
            }
        } catch (err) {
            setError('Invalid credentials');
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
            />
            <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
            />
            {error && <p>{error}</p>}
            <button onClick={handleLogin}>Login</button>
        </div>
    );
};

export default LoginPage;
