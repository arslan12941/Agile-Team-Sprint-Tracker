import React, { useEffect, useState } from 'react';
import { logout } from '../utils/authUtils';
import axios from '../utils/axios';

const UserDashboard = () => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        // Fetch assigned tasks for user
        axios.get('/tasks').then((response) => setTasks(response.data)).catch((error) => {
            if (error.response.status === 401) {
                logout();
            }
        });
    }, []);

    return (
        <div>
            <h2>User Dashboard</h2>
            <button onClick={logout}>Logout</button>
            <h3>Assigned Tasks</h3>
            <ul>
                {tasks.map((task) => (
                    <li key={task.id}>
                        {task.title} - {task.status}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UserDashboard;
