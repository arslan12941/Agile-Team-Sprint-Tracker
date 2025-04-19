import React, { useState } from 'react';
import { loginWithEmail, loginWithGitHub } from '../Services/authservice';
import { useHistory } from 'react-router-dom';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useHistory();

    const handleEmailLogin = async () => {
        try {
            const token = await loginWithEmail(email, password);
            if (token) {
                // Redirect the user to the dashboard after successful login
                history.push('/dashboard');
            }
        } catch (err) {
            setError('Invalid credentials');
        }
    };

    const handleGitHubLogin = async () => {
        // Call GitHub OAuth login function
        loginWithGitHub();
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
            <button onClick={handleEmailLogin}>Login</button>
            <button onClick={handleGitHubLogin}>Continue with GitHub</button>
        </div>
    );
};

export default LoginPage;
