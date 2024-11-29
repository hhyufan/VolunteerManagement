import React from 'react';
import PropTypes from 'prop-types';
import { Card, CardContent, Typography, CardActions, Button } from '@mui/material';

const VolunteerCard = ({ volunteer, onDelete, onEdit, isLoggedIn }) => {
    return (
        <Card sx={{ minWidth: 275, marginBottom: 2 }}>
            <CardContent>
                <Typography variant="h5" component="div">{volunteer.name}</Typography>
                <Typography color="text.secondary">Email: {volunteer.email}</Typography>
                <Typography color="text.secondary">Phone: {volunteer.phone}</Typography>
            </CardContent>
            {isLoggedIn && (
                <CardActions>
                    <Button size="small" onClick={() => onEdit(volunteer)}>Edit</Button>
                    <Button size="small" onClick={() => onDelete(volunteer.id)}>Delete</Button>
                </CardActions>
            )}
        </Card>
    );
};

VolunteerCard.propTypes = {
    volunteer: PropTypes.shape({
        id: PropTypes.number.isRequired,
        name: PropTypes.string.isRequired,
        email: PropTypes.string.isRequired,
        phone: PropTypes.string.isRequired,
    }).isRequired,
    onDelete: PropTypes.func.isRequired,
    onEdit: PropTypes.func.isRequired,
    isLoggedIn: PropTypes.bool.isRequired,
};

export default VolunteerCard;
