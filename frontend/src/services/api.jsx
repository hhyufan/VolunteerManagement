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

export const fetchAdmins = async (username) => {
    try {
        const response = await axios.get("/api/admin", {
            params: {
                action: 'fetch' ,
                username
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching volunteers:', error);
        throw error;
    }
};

export const getVolunteerByName = async (name) => {
    try {
        const response = await axios.get(`${API_URL}/select`, {
            params: { name }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching volunteer by name:', error);
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
                username,
                password
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error logging in:', error);
        throw error;
    }
};

// 注册
export const registerAdmin = async (username, password, invitationCode, phone, email) => {
    try {
        console.log(invitationCode)
        const response = await axios.get('/api/admin', {
            params: {
                action: 'register',
                username,
                password,
                invitationCode,
                phone,
                email
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error registering:', error);
        throw error;
    }
};
