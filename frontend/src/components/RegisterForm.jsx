import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Modal, Link } from '@mui/material';
import { registerAdmin } from '../services/api'; // 假设你已经有一个 registerAdmin 的 API 方法
import "../theme/style.scss"

const RegisterForm = ({ onClose, onSuccess, setShowLogin }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 检查密码和确认密码是否匹配
        if (password !== confirmPassword) {
            setError('密码不一致！');
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

    const handleSwitchToLogin = () => {
        setShowLogin(true); // 切换到登录表单
        onClose(); // 关闭注册表单
    };

    return (
        <Modal open onClose={onClose}>
            <Box className="modal">
                <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
                    <Typography variant="h6" component="h2">Register</Typography>
                    <Typography variant="body1">
                        已有账户?{' '}
                        <Link component="button" variant="body1" onClick={handleSwitchToLogin}>
                            去登录
                        </Link>
                    </Typography>
                </Box>
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
