import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import LoginPage from './components/LoginPage';
import AdminDashboard from './components/AdminDashboard';
import UserDashboard from './components/UserDashboard';
import { isTokenExpired } from './utils/authUtils';

const App = () => {
    const tokenExpired = isTokenExpired();

    return (
        <Router>
            <Switch>
                <Route path="/login" component={LoginPage} />
                <Route
                    path="/admin-dashboard"
                    render={() => tokenExpired ? <Redirect to="/login" /> : <AdminDashboard />}
                />
                <Route
                    path="/user-dashboard"
                    render={() => tokenExpired ? <Redirect to="/login" /> : <UserDashboard />}
                />
                <Redirect from="/" to="/login" />
            </Switch>
        </Router>
    );
};

export default App;
