import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Modal } from '@mui/material';
import { loginAdmin } from '../services/api';

const modalStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    borderRadius: 1.5,
    bgcolor: 'background.paper',
    boxShadow: 24,
    p: 4,

};

const LoginForm = ({ onClose, onSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await loginAdmin(username, password);
            if (response.success) {
                onSuccess(username);
            } else {
                alert(response.message);
            }
        } catch (error) {
            alert('Login failed');
        }
    };

    return (
        <Modal open onClose={onClose}>
            <Box sx={modalStyle}>
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
                    <Button type="submit" variant="contained" color="primary" fullWidth>Login</Button>
                </form>
            </Box>
        </Modal>
    );
};

export default LoginForm;
