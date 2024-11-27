import axios from 'axios';
const API_URL = '/api/volunteers';

export const fetchVolunteers = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching volunteers:', error);
        throw error;
    }
};

export const deleteVolunteer = async (id) => {
    try {
        await axios.get(`${API_URL}/delete`, {
            params: { id }
        });
    } catch (error) {
        console.error('Error deleting volunteer:', error);
        throw error;
    }
};

export const updateVolunteer = async (volunteer) => {
    try {
        await axios.get(`${API_URL}/update`, {
            params: volunteer
        });
    } catch (error) {
        console.error('Error updating volunteer:', error);
        throw error;
    }
};

export const addVolunteer = async (volunteer) => {
    try {
        await axios.get(`${API_URL}/add`, {
            params: volunteer
        });
    } catch (error) {
        console.error('Error adding volunteer:', error);
        throw error;
    }
};

// 登录
export const loginAdmin = async (username, password) => {
    try {
        const response = await axios.get('/api/admin', {
            params: {
                action: 'login',
                username: encodeURIComponent(username),
                password: encodeURIComponent(password)
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error logging in:', error);
        throw error;
    }
};

// 注册
export const registerAdmin = async (username, password) => {
    try {
        const response = await axios.get('/api/admin', {
            params: {
                action: 'register',
                username: encodeURIComponent(username),
                password: encodeURIComponent(password)
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error registering:', error);
        throw error;
    }
};
