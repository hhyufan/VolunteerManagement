import { useState } from 'react';
import PropTypes from 'prop-types';

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
        <form onSubmit={handleSubmit} className="volunteer-form">
            <input
                type="text"
                name="name"
                value={volunteer.name || ''}
                onChange={handleChange}
                placeholder="Name"
                required
            />
            <input
                type="email"
                name="email"
                value={volunteer.email || ''}
                onChange={handleChange}
                placeholder="Email"
                required
            />
            <input
                type="text"
                name="phone"
                value={volunteer.phone || ''}
                onChange={handleChange}
                placeholder="Phone"
                required
            />
            <button type="submit">Submit</button>
        </form>
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
