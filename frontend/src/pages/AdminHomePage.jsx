// src/VolunteerManagerHome.jsx

import React from 'react';
import {
    Typography,
    Container,
    Box,
    List,
    ListItem,
    ListItemIcon,
    ListItemText, Grid2, Alert
} from '@mui/material';

import {Link, Outlet, useOutletContext} from 'react-router-dom';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPlus, faUser} from "@fortawesome/free-solid-svg-icons";

const AdminHomePage = () => {
    const { isLoggedIn , user } = useOutletContext();
    return (
        <Box sx={{ flexDirection: 'column', minHeight: '100vh', backgroundColor: '#f4f4f9' }}>
            {/* Main Content */}
            {isLoggedIn ? (<Container sx={{  padding: '20px' }}>
                <Grid2 container spacing={2}>
                    <Grid2 item xs={3}>
                        {/* Sidebar */}
                        <Box sx={{ backgroundColor: '#f8f9fa', padding: '20px', boxShadow: '2px 0 5px rgba(0, 0, 0, 0.1)' }}>
                            <Typography variant="h6">Menu</Typography>
                            <List>
                                <ListItem button component={Link} to="admin-info" className="nav-link">
                                    <ListItemIcon><FontAwesomeIcon icon={faUser} /></ListItemIcon>
                                    <ListItemText primary="个人信息" />
                                </ListItem>
                                <ListItem button component={Link} to="code-add" className="nav-link">
                                    <ListItemIcon><FontAwesomeIcon icon={faPlus} /></ListItemIcon>
                                    <ListItemText primary="创建邀请码" />
                                </ListItem>
                            </List>
                        </Box>
                    </Grid2>
                    <Grid2  xs={9}>
                        {/* Content Area */}
                        <Box sx={{ backgroundColor: 'white', borderRadius: '8px', boxShadow: '0 0 24px rgba(0, 0, 0, 0.1)' }}>
                            <Outlet context={{ user}}/>
                        </Box>
                    </Grid2>
                </Grid2>
            </Container>)
            : (
            <Alert severity="warning">请先登录以查看个人信息。</Alert>
            )}
        </Box>
    );
};

export default AdminHomePage;

