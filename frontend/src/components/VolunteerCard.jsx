import PropTypes from 'prop-types';

const VolunteerCard = ({ volunteer, onDelete, onEdit }) => {
    return (
        <div className="volunteer-card">
            <h3>{volunteer.name}</h3>
            <p>Email: {volunteer.email}</p>
            <p>Phone: {volunteer.phone}</p>
            <button onClick={() => onEdit(volunteer)}>Edit</button>
            <button onClick={() => onDelete(volunteer.id)}>Delete</button>
        </div>
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
};

export default VolunteerCard;
