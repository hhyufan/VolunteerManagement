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
        // alert(JSON.stringify(response.data));
        return response.data;
    } catch (error) {
        console.error('Error fetching volunteers:', error);
        throw error;
    }
};

export const fetchEvents = async () => {
    try {
        const response = await axios.get("/api/events");
        // alert(JSON.stringify(response.data));
        return response.data;
    } catch (error) {
        console.error('Error fetching volunteers:', error);
        throw error;
    }
};

export const fetchInvitationCode = async (username) => {
    try {
        const response = await axios.get("/api/invitationCode", {
            params: {
                username
            }
        });
        return response.data;
    } catch (error) {
        console.error(`Error fetching invitation codes:`, error);
        throw error;
    }
}

export const fetchInvitationCodes = async () => {
    try {
        const response = await axios.get("/api/invitationCodes");
        return response.data;
    } catch (error) {
        console.error(`Error fetching invitation codes:`, error);
        throw error;
    }
}


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

export const getEventByTitle = async (title) => {
    try {
        const response = await axios.get(`api/events/select`, {
            params: { title }
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

export const addEvent = async (event) => {
    try {
        const response = await axios.get(`api/events/add`, {
            params: {
                title: event.title,
                date: event.date,
                location: event.location,
                duration: event.duration,
                content: event.content,
                attachmentLink: event.attachmentLink,
                imageUrl: event.imageUrl
            }
        });
        return response.data; // 返回服务器的响应数据
    } catch (error) {
        console.error('Error adding event:', error);
        throw error; // 抛出错误以供调用者处理
    }
};

export const addInvitationCode = async (invitationCode, username) => {
    await axios.get(`/api/invitationCode/add`, {
        params: {
            invitationCode,
            username
        }
    });
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
export const registerAdmin = async (username, password, phone, email, invitationCode) => {
    try {
        const response = await axios.get('/api/admin', {
            params: {
                action: 'register',
                username,
                password,
                phone,
                email,
                invitationCode
            },
            headers: {
                'Content-Type': 'application/json',
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error registering:', error);
        throw error;
    }
};
// deleteEvent
export const deleteEvent = async (title) => {
    try {
        const response = await axios.get(`api/events/delete`, {
            params: { title },
        });
        return response.data;
    } catch (error) {
        console.error('Error deleting event:', error);
        throw error;
    }
};


// updateEvent
export const updateEvent = async (event) => {
    try {
        const response = await axios.get(`api/events/update`, {
            params: {
                id: event.id,
                title: event.title,
                date: event.date,
                location: event.location,
                duration: event.duration,
                content: event.content,
                attachmentLink: event.attachmentLink,
                imageUrl: event.imageUrl
            },
        });
        return response.data;
    } catch (error) {
        console.error('Error updating event:', error);
        throw error;
    }
};
