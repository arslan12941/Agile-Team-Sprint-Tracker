import React, { useState, useEffect } from 'react';
import { getAllSprints, createSprint, updateSprint } from '../Services/sprintService';

const AdminDashboard = () => {
    const [sprints, setSprints] = useState([]);
    const [newSprint, setNewSprint] = useState({ title: '', description: '' });

    useEffect(() => {
        const fetchSprints = async () => {
            const data = await getAllSprints();
            setSprints(data);
        };
        fetchSprints();
    }, []);

    const handleCreateSprint = async () => {
        const data = await createSprint(newSprint);
        setSprints((prevSprints) => [...prevSprints, data]);
    };

    return (
        <div>
            <h1>Admin Dashboard</h1>
            <div>
                <input
                    type="text"
                    placeholder="Sprint Title"
                    value={newSprint.title}
                    onChange={(e) => setNewSprint({ ...newSprint, title: e.target.value })}
                />
                <textarea
                    placeholder="Sprint Description"
                    value={newSprint.description}
                    onChange={(e) => setNewSprint({ ...newSprint, description: e.target.value })}
                />
                <button onClick={handleCreateSprint}>Create Sprint</button>
            </div>
            <div>
                <h2>All Sprints</h2>
                {sprints.map((sprint) => (
                    <div key={sprint.id}>
                        <h3>{sprint.title}</h3>
                        <p>{sprint.description}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default AdminDashboard;
