import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Modal } from '@mui/material';
import { loginAdmin } from '../services/api';
import "../theme/style.scss"
const LoginForm = ({ onClose, onSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await loginAdmin(username, password);
            if (response.success) {
                onSuccess(username);
            } else {
                setError(response.message || 'Login failed');
            }
        } catch (error) {
            setError('Login failed');
        }
    };

    return (
        <Modal open onClose={onClose}>
            <Box className="modal">
                <Typography variant="h6" component="h2" mb={2}>Login</Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        fullWidth
                        variant="outlined"
                        margin="normal"
                        label="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                    <TextField
                        fullWidth
                        variant="outlined"
                        margin="normal"
                        label="Password"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    {error && <Typography color="error" variant="body2" mt={1}>{error}</Typography>}
                    <Button type="submit" variant="contained" color="primary" fullWidth>Login</Button>
                </form>
            </Box>
        </Modal>
    );
};

export default LoginForm;
