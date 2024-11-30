import React, { useState } from 'react';
import { useOutletContext, useNavigate } from 'react-router-dom';
import VolunteerForm from '../components/VolunteerForm';
import { addVolunteer, getVolunteerByName } from '../services/api';
import { Box, Typography, Alert } from '@mui/material';

const AddVolunteerPage = () => {
    const { isLoggedIn } = useOutletContext();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({ name: '', email: '', phone: '' });

    const handleSubmit = async (volunteer) => {
        const existingVolunteer = await getVolunteerByName(volunteer.name);
        if (existingVolunteer) {
            alert('已存在志愿者!');
            setFormData({ name: '', email: '', phone: '' }); // 清空表单
            return;
        }
        await addVolunteer(volunteer);
        alert('志愿者添加成功!');
        navigate("/volunteers");
    };

    return (
        <Box sx={{ p: 3 }}>
            <Typography variant="h4" gutterBottom>添加新志愿者</Typography>
            {isLoggedIn ? (
                <VolunteerForm
                    initialData={formData}
                    onSubmit={handleSubmit}
                />
            ) : (
                <Alert severity="warning">请先登录以添加志愿者。</Alert>
            )}
        </Box>
    );
};

export default AddVolunteerPage;
