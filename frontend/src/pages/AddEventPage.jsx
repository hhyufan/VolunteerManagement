import React, { useState } from 'react';
import { useOutletContext, useNavigate } from 'react-router-dom';
import EventForm from '../components/EventForm.jsx'; // 假设您有一个用于事件的表单组件
import { addEvent, getEventByTitle } from '../services/api'; // 假设您有这些 API 函数
import { Box, Typography, Alert } from '@mui/material';

const AddEventPage = () => {
    const { isLoggedIn } = useOutletContext();
    const [formData, setFormData] = useState({
        id: '',
        title: '',
        date: '',
        location: '',
        duration: '',
        content: '',
        attachmentLink: '',
        imageUrl: ''
    });

    const handleSubmit = async (event) => {
        const existingEvent = await getEventByTitle(event.title);
        if (existingEvent) {
            alert('已存在同名事件!');
            setFormData({
                id: '',
                title: '',
                date: '',
                location: '',
                duration: '',
                content: '',
                attachmentLink: '',
                imageUrl: ''
            }); // 清空表单
            return;
        }
        event.date += ":00";
        await addEvent(event);
        alert('事件添加成功!');
    };

    return (
        <Box sx={{ p: 3 }}>
            <Typography variant="h4" gutterBottom>添加新事件</Typography>
            {isLoggedIn ? (
                <EventForm
                    initialData={formData}
                    onSubmit={handleSubmit}
                />
            ) : (
                <Alert severity="warning">请先登录以添加事件。</Alert>
            )}
        </Box>
    );
};

export default AddEventPage;
