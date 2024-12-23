import React, { useEffect, useState } from 'react';
import {Link, useOutletContext} from 'react-router-dom';
import EventCard from '../components/EventCard';
import EventForm from '../components/EventForm';
import { fetchEvents, deleteEvent, updateEvent, addEvent } from '../services/api';
import {Alert, Box, Grid2, Typography} from '@mui/material';




String.prototype.toDuration = function ()  {
    const [, hours = 0, minutes = 0, seconds = 0] =
    this.match(/(?:(\d+)小时)?(?:(\d+)分)?(?:(\d+)秒)?/) ?? [];
    return (+hours * 3600) + (+minutes * 60) + (+seconds);
};
const EventListPage = () => {
    const { isLoggedIn } = useOutletContext();
    const [events, setEvents] = useState([]);
    const [editingEvent, setEditingEvent] = useState(null);

    useEffect(() => {
        loadEvents();
    }, []);

    const loadEvents = async () => {
        const data = await fetchEvents();
        setEvents(data);
    };

    const handleDelete = async (title) => {
        await deleteEvent(title);
        setEditingEvent(null);
        await loadEvents();
    };

    const handleEdit = (event) => {
        setEditingEvent(event);
    };

    const handleSubmit = async (event) => {
        let eventCopy = {...event}
        console.log(event.date)
        eventCopy.duration = event.duration.toDuration();
        eventCopy.date = event.date + ":00";
        if (eventCopy.id) {
            await updateEvent(eventCopy);
        } else {
            await addEvent(eventCopy);
        }
        setEditingEvent(null);
        await loadEvents();
    };

    return (
        <Box sx={{ p: 3 }}>
            {editingEvent && events.length > 0 &&
                <EventForm initialData={editingEvent} onSubmit={handleSubmit} />
            }

            {events.length > 0 ? (<Grid2 container spacing={2}>
                {events.map(event => (
                    <Grid2 key={event.id} item xs={12} sm={6} md={4}>
                        <EventCard
                            event={event}
                            onDelete={handleDelete}
                            onEdit={handleEdit}
                            isLoggedIn={isLoggedIn}
                        />
                    </Grid2>
                ))}
            </Grid2>): (<Alert severity="warning" >Please first <Typography component={Link} to="../add-event">add</Typography> an event.</Alert>)
            }
        </Box>
    );
};

export default EventListPage;
