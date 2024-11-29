import { Link, Outlet } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faUsers, faPlus } from '@fortawesome/free-solid-svg-icons';
import LoginForm from "./LoginForm.jsx";
import RegisterForm from "./RegisterForm.jsx";
import React, { useState } from "react";

const Layout = () => {
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
    const handleLogout = () => {
        setUser(null); // 清除用户信息
    };
    const isLoggedIn = !!user; // 检查用户是否已登录
    return (
        <div className="layout">
            {user ? (
                <div className="welcome-message">
                    欢迎您, {user}
                    <button onClick={handleLogout}>退出</button>
                </div>

            ) : (
                <div className="auth-buttons">
                    <button onClick={() => setShowLogin(true)}>登录</button>
                    <button onClick={() => setShowRegister(true)}>注册</button>
                </div>
            )}



            {showLogin && <LoginForm onClose={() => setShowLogin(false)} onSuccess={handleLoginSuccess} />}
            {showRegister && <RegisterForm onClose={() => setShowRegister(false)} onSuccess={handleRegisterSuccess} />}
            <header className="header">
                <h1>志愿者管理系统</h1>
            </header>
            <div className="main-content">
                {user && (
                    <nav className="sidebar">
                        <Link to="/">
                            <FontAwesomeIcon icon={faHome}/> 首页
                        </Link>
                        <Link to="/volunteers">
                            <FontAwesomeIcon icon={faUsers}/> 管理志愿者
                        </Link>
                        <Link to="/add-volunteer">
                            <FontAwesomeIcon icon={faPlus}/> 添加志愿者
                        </Link>
                    </nav>
                )}
                <div className="content">
                    <Outlet context={{ isLoggedIn }}/>
                </div>
            </div>
            <footer className="footer">
                <p>© 2024 志愿者管理系统</p>
            </footer>
        </div>
    );
};

export default Layout;
