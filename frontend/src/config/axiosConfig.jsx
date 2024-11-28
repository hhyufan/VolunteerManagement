import axios from 'axios';

axios.interceptors.request.use(
    config => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`; // 将 JWT 附加到请求头
        }
        return config;
    },
    error => {
        console.error('Error logging in:', error);
        return Promise.reject(error);
    }
);

export default axios;
