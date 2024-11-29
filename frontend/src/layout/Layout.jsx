import React, { useState } from 'react';
import { Link, Outlet,  useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faUsers, faPlus } from '@fortawesome/free-solid-svg-icons';
import { AppBar, Toolbar, Typography, Button, Drawer, List, ListItem, ListItemIcon, ListItemText, Box } from '@mui/material';
import LoginForm from '../components/LoginForm.jsx';
import RegisterForm from '../components/RegisterForm.jsx';
import '../theme/style.css';

const drawerWidth = 240;

const Layout = () => {
    const navigate = useNavigate()
    const [showLogin, setShowLogin] = useState(false);
    const [showRegister, setShowRegister] = useState(false);
    const [user, setUser] = useState(null);

    const handleLoginSuccess = (username) => {
        setUser(username);
        setShowLogin(false);
    };

    const handleRegisterSuccess = (username) => {
        setUser(username);
        setShowRegister(false);
    };

    const handleLogout = () => {
        setUser(null);
        navigate('/');
    };

    const isLoggedIn = !!user;

    return (
        <Box className="layout">
            <AppBar position="static" className="header">
                <Toolbar>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        志愿者管理系统
                    </Typography>
                    {user ? (
                        <Box>
                            <Typography variant="body1" sx={{ display: 'inline', marginRight: 2 }}>
                                欢迎您, {user}
                            </Typography>
                            <Button color="inherit" onClick={handleLogout}>退出</Button>
                        </Box>
                    ) : (
                        <Box>
                            <Button color="inherit" onClick={() => setShowLogin(true)}>登录</Button>
                            <Button color="inherit" onClick={() => setShowRegister(true)}>注册</Button>
                        </Box>
                    )}
                </Toolbar>
            </AppBar>

            {showLogin && <LoginForm onClose={() => setShowLogin(false)} onSuccess={handleLoginSuccess} />}
            {showRegister && <RegisterForm onClose={() => setShowRegister(false)} onSuccess={handleRegisterSuccess} />}

            <Box className="main-content">
                {user && (
                    <Drawer
                        className="sidebar"
                        variant="permanent"
                        anchor="left"
                        sx={{
                            width: drawerWidth,
                            flexShrink: 0,
                            '& .MuiDrawer-paper': {
                                width: drawerWidth,
                                boxSizing: 'border-box',
                            },
                        }}
                    >
                        <List>
                            <ListItem button component={Link} to="/" className="nav-link">
                                <ListItemIcon><FontAwesomeIcon icon={faHome} /></ListItemIcon>
                                <ListItemText primary="首页" />
                            </ListItem>
                            <ListItem button component={Link} to="/volunteers" className="nav-link">
                                <ListItemIcon><FontAwesomeIcon icon={faUsers} /></ListItemIcon>
                                <ListItemText primary="管理志愿者" />
                            </ListItem>
                            <ListItem button component={Link} to="/add-volunteer" className="nav-link">
                                <ListItemIcon><FontAwesomeIcon icon={faPlus} /></ListItemIcon>
                                <ListItemText primary="添加志愿者" />
                            </ListItem>
                        </List>
                    </Drawer>
                )}
                <Box component="main" className="content" sx={{ flexGrow: 1, p: 3 }}>
                    <Outlet context={{ isLoggedIn }} />
                </Box>
            </Box>

            <Box component="footer" className="footer">
                <Typography variant="body2">© 2024 志愿者管理系统</Typography>
            </Box>
        </Box>
    );
};

export default Layout;
