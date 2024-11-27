import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import LoginForm from '../components/LoginForm';
import RegisterForm from '../components/RegisterForm';

const HomePage = () => {
    const [showLogin, setShowLogin] = useState(false);
    const [showRegister, setShowRegister] = useState(false);
    const [user, setUser] = useState(null); // 用于存储登录用户信息

    const handleLoginSuccess = (username) => {
        setUser(username);
        setShowLogin(false);
    };

    const handleRegisterSuccess = (username) => {
        setUser(username);
        setShowRegister(false);
    };

    return (
        <div>
            <h1>欢迎来到志愿者管理系统</h1>
            {user ? (
                <div className="welcome-message">欢迎您, {user}</div>
            ) : (
                <div className="auth-buttons">
                    <button onClick={() => setShowLogin(true)}>登录</button>
                    <button onClick={() => setShowRegister(true)}>注册</button>
                </div>
            )}
            {showLogin && <LoginForm onClose={() => setShowLogin(false)} onSuccess={handleLoginSuccess} />}
            {showRegister && <RegisterForm onClose={() => setShowRegister(false)} onSuccess={handleRegisterSuccess} />}
        </div>
    );
};

export default HomePage;
