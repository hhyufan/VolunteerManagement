import React from 'react';
import { useOutletContext } from 'react-router-dom';
import VolunteerForm from '../components/VolunteerForm';
import { addVolunteer } from '../services/api';
import { Box, Typography, Alert } from '@mui/material';

const AddVolunteerPage = () => {
    const { isLoggedIn } = useOutletContext();

    const handleSubmit = async (volunteer) => {
        await addVolunteer(volunteer);
        alert('志愿者添加成功!');
    };

    return (
        <Box sx={{ p: 3 }}>
            <Typography variant="h4" gutterBottom>添加新志愿者</Typography>
            {isLoggedIn ? (
                <VolunteerForm onSubmit={handleSubmit} />
            ) : (
                <Alert severity="warning">请先登录以添加志愿者。</Alert>
            )}
        </Box>
    );
};

export default AddVolunteerPage;
