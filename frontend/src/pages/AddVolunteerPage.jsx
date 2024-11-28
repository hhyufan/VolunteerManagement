import VolunteerForm from '../components/VolunteerForm';
import { addVolunteer } from '../services/api';
import {useOutletContext} from "react-router-dom";

const AddVolunteerPage = () => {
    const { isLoggedIn } = useOutletContext();
    const handleSubmit = async (volunteer) => {
        await addVolunteer(volunteer);
        alert('志愿者添加成功!');
    };

    return (
        <div>
            <h1>添加新志愿者</h1>
            {isLoggedIn ? (
                <VolunteerForm onSubmit={handleSubmit} />
            ) : (
                <p>请先登录以添加志愿者。</p>
            )}
        </div>
    );
};

export default AddVolunteerPage;
