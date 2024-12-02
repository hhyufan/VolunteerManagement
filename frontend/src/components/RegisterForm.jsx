import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Modal, Link } from '@mui/material';
import { registerAdmin } from '../services/api'; // 假设你已经有一个 registerAdmin 的 API 方法
import "../theme/style.scss"

const RegisterForm = ({ onClose, onSuccess, setShowLogin }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');

    // 计算密码强度
    const passwordStrength = ((password) => {
        let strength = 0;
        if (password.length >= 8) strength++;
        if (/[A-Z]/.test(password)) strength++;
        if (/[a-z]/.test(password)) strength++;
        if (/\d/.test(password)) strength++;
        if (/[!@#$%^&*]/.test(password)) strength++;
        return strength; // 返回强度等级 0-5
    })(password);

    const getStrengthInfo = () => {
        if (passwordStrength < 2) return { color: '#ff1111', text: '弱' , level: 1};
        if (passwordStrength < 4) return { color: '#FFD700', text: '中', level: 2 };
        return { color: '#00FF00', text: '强' , level: 3};
    };

    const { color, text, level } = getStrengthInfo();

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 检查密码和确认密码是否匹配
        if (password !== confirmPassword) {
            setError('密码不一致！');
            return;
        }
        // 检查密码强度
        if (level === 1) {
            setError("密码强度太弱！");
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
                <Box className= "modal-header">
                    <Typography variant="h6" component="h2">注册</Typography>
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
                    <TextField
                        fullWidth
                        variant="outlined"
                        margin="normal"
                        label="确认密码"
                        type="password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                    {/* 密码强度条 */}
                    <Box display= {!password ? "none": "flex"} justifyContent="space-between" alignItems="center" mt={0.5} mb={1}>
                        <Box
                            sx={{
                                flex: 1,
                                height: '5px',
                                backgroundColor: level >= 1 ? color : 'transparent'
                            }}
                        />
                        <Box
                            sx={{
                                flex: 1,
                                height: '5px',
                                backgroundColor: level >= 2 ? color : 'transparent'
                            }}
                        />
                        <Box
                            sx={{
                                flex: 1,
                                height: '5px',
                                backgroundColor: level >= 3 ? color : 'transparent'
                            }}
                        />
                        <Typography
                            color={color} textAlign= "center"
                            ml = {1}
                        >
                            {text}
                        </Typography>
                    </Box>
                    {error && <Typography color="error" variant="body2" mt={1}>{error}</Typography>}
                    <Button type="submit" variant="contained" color="primary" fullWidth>Register</Button>
                </form>
            </Box>
        </Modal>
    );
};

export default RegisterForm;
