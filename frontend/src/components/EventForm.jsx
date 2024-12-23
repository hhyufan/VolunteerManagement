import React, { useEffect, useState } from 'react';
import { Box, Button, TextField } from '@mui/material';



const EventForm = ({ initialData = {}, onSubmit }) => {
    const [event, setEvent] = useState(initialData);

    useEffect(() => {
        // 当 initialData 变化时，更新 event 状态
        setEvent(initialData);
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEvent({ ...event, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(event);
    };
    return (
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
            <TextField
                fullWidth
                label="Title"
                name="title"
                value={event.title || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <TextField
                fullWidth
                label="Date"
                name="date"
                type="datetime-local"
                value={event.date || ''}
                onChange={handleChange}
                margin="normal"
                sx={{
                    '& .MuiInputBase-root': {
                        paddingLeft: '46px', // 调整输入框的左内边距
                    }
                }}
                required
            />
            <TextField
                fullWidth
                label="Location"
                name="location"
                value={event.location || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <TextField
                fullWidth
                label="Duration [reg:(\d+时)?(\d+分)?(\d+秒)?]"
                name="duration"
                value={event.duration || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <TextField
                fullWidth
                label="Content"
                name="content"
                value={event.content || ''}
                onChange={handleChange}
                margin="normal"
                multiline
                rows={4}
                required
            />
            <TextField
                fullWidth
                label="Attachment Link"
                name="attachmentLink"
                value={event.attachmentLink || ''}
                onChange={handleChange}
                margin="normal"
            />
            <TextField
                fullWidth
                label="Image URL"
                name="imageUrl"
                value={event.imageUrl || ''}
                onChange={handleChange}
                margin="normal"
            />
            <Button type="submit" variant="contained" color="primary" fullWidth>Submit</Button>
        </Box>
    );
};

export default EventForm;
