import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Modal, Link } from '@mui/material';
import { loginAdmin } from '../services/api';
import "../theme/style.scss"

const LoginForm = ({ onClose, onSuccess, setShowRegister }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response =  await loginAdmin(username, password);
            if (response.success) {
                onSuccess(username);
            } else {
                setError(response.message || 'Login failed');
            }
        } catch (error) {
            setError('Login failed');
        }
    };

    const handleSwitchToRegister = () => {
        setShowRegister(true); // 切换到注册表单
        onClose(); // 关闭登录表单
    };

    return (
        <Modal open onClose={onClose}>
            <Box className="modal">
                <Box className= "modal-header">
                    <Typography variant="h6" component="h2">登录</Typography>
                    <Typography variant="body1">
                        没有账户?{' '}
                        <Link component="button" variant="body1" onClick={handleSwitchToRegister}>
                            去注册
                        </Link>
                    </Typography>
                </Box>
                <form onSubmit={handleSubmit}>
                    <TextField
                        fullWidth
                        variant="outlined"
                        margin="normal"
                        label="用户名"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                    <TextField
                        fullWidth
                        variant="outlined"
                        margin="normal"
                        label="密码"
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
