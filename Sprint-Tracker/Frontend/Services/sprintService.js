import axios from '../utils/axios';

// Get all sprints (Admin can fetch all)
export const getSprints = async () => {
    try {
        const response = await axios.get('/sprints');
        return response.data;
    } catch (error) {
        throw new Error('Failed to fetch sprints');
    }
};

// Create a new sprint (Admin only)
export const createSprint = async (data) => {
    try {
        const response = await axios.post('/sprints', data);
        return response.data;
    } catch (error) {
        throw new Error('Failed to create sprint');
    }
};

// Update sprint (Admin only)
export const updateSprint = async (id, data) => {
    try {
        const response = await axios.put(`/sprints/${id}`, data);
        return response.data;
    } catch (error) {
        throw new Error('Failed to update sprint');
    }
};
