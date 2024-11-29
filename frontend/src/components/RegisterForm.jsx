import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Modal } from '@mui/material';
import { registerAdmin } from '../services/api'; // 假设你已经有一个 registerAdmin 的 API 方法

const modalStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    boxShadow: 24,
    p: 4,
    borderRadius: 1.5
};

const RegisterForm = ({ onClose, onSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 检查密码和确认密码是否匹配
        if (password !== confirmPassword) {
            setError('Passwords do not match');
            return;
        }

        try {
            const response = await registerAdmin(username, password);
            if (response.success) {
                onSuccess(username); // 成功注册后，调用 onSuccess 回调
            } else {
                setError(response.message || 'Registration failed');
            }
        } catch (error) {
            setError('Registration failed');
        }
    };

    return (
        <Modal open onClose={onClose}>
            <Box sx={modalStyle}>
                <Typography variant="h6" component="h2" mb={2}>Register</Typography>
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
                    <TextField
                        fullWidth
                        variant="outlined"
                        margin="normal"
                        label="Confirm Password"
                        type="password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                    {error && <Typography color="error" variant="body2" mt={1}>{error}</Typography>}
                    <Button type="submit" variant="contained" color="primary" fullWidth>Register</Button>
                </form>
            </Box>
        </Modal>
    );
};

export default RegisterForm;
