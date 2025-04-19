import React, { useEffect, useState } from 'react';
import { logout } from '../utils/authUtils';
import { getSprints } from '../Services/sprintService';

const AdminDashboard = () => {
    const [sprints, setSprints] = useState([]);

    useEffect(() => {
        // Fetch all sprints for admin
        getSprints().then(setSprints).catch((error) => {
            console.error(error);
            logout();
        });
    }, []);

    return (
        <div>
            <h2>Admin Dashboard</h2>
            <button onClick={logout}>Logout</button>
            <h3>Sprints</h3>
            <ul>
                {sprints.map((sprint) => (
                    <li key={sprint.id}>
                        {sprint.title} - {sprint.status}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AdminDashboard;
