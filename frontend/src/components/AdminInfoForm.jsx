import React, {useEffect, useState} from 'react';
import {
    Box,
    Typography,
    List,
    ListItem,
    ListItemText,
    Paper,
    Divider
} from '@mui/material';
import {fetchAdmins, fetchVolunteers} from "../services/api.jsx";
import {useOutletContext} from "react-router-dom";

const AdminInfoForm = () => {
    const [admin, setAdmin] = useState([]);
    const { user } = useOutletContext();
    // 假设这些是要显示的个人信息
    const loadAdmin = async () => {
        const data = await fetchAdmins(user);
        setAdmin(data);
    };

    useEffect(() => {
        loadAdmin()
    }, []);
    return (

            <Paper
                sx={{ padding: '20px' }}
            >
                <List>
                    <ListItem>
                        <ListItemText primary="姓名" secondary={admin.username} />
                    </ListItem>
                    <Divider />
                    <ListItem>
                        <ListItemText primary="电子邮件" secondary={admin.email} />
                    </ListItem>
                    <Divider />
                    <ListItem>
                        <ListItemText primary="电话" secondary={admin.phone} />
                    </ListItem>
                </List>
            </Paper>
    );

};

export default AdminInfoForm;