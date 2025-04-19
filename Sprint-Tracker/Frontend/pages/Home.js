import React from 'react';
import { useHistory } from 'react-router-dom';

const Home = () => {
    const history = useHistory();

    const handleGetStarted = () => {
        history.push('/login');
    };

    return (
        <div>
            <h1>Welcome to Agile Team Sprint Tracker</h1>
            <button onClick={handleGetStarted}>Get Started</button>
        </div>
    );
};

export default Home;
