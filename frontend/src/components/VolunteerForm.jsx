import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Box, Button, TextField } from '@mui/material';

const VolunteerForm = ({ initialData = {}, onSubmit }) => {
    const [volunteer, setVolunteer] = useState(initialData);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setVolunteer({ ...volunteer, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(volunteer);
    };

    return (
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
            <TextField
                fullWidth
                label="Name"
                name="name"
                value={volunteer.name || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <TextField
                fullWidth
                label="Email"
                name="email"
                type="email"
                value={volunteer.email || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <TextField
                fullWidth
                label="Phone"
                name="phone"
                value={volunteer.phone || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <Button type="submit" variant="contained" color="primary" fullWidth>Submit</Button>
        </Box>
    );
};

VolunteerForm.propTypes = {
    initialData: PropTypes.shape({
        name: PropTypes.string,
        email: PropTypes.string,
        phone: PropTypes.string,
    }),
    onSubmit: PropTypes.func.isRequired,
};

export default VolunteerForm;
