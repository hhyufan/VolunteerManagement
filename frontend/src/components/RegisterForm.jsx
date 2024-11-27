import React, { useState } from 'react';
import { registerAdmin } from '../services/api';

const RegisterForm = ({ onClose, onSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await registerAdmin(username, password);
            if (response.success) {
                onSuccess(username);
            } else {
                alert(response.message);
            }
        } catch (error) {
            alert('Registration failed');
        }
    };

    return (
        <div className="modal">
            <div className="modal-content">
                <span className="close" onClick={onClose}>&times;</span>
                <h2>Register</h2>
                <form onSubmit={handleSubmit}>
                    <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} required />
                    <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    <button type="submit">Register</button>
                </form>
            </div>
        </div>
    );
};

export default RegisterForm;
