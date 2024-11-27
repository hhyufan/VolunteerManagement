import VolunteerForm from '../components/VolunteerForm';
import { addVolunteer } from '../services/api';

const AddVolunteerPage = () => {
    const handleSubmit = async (volunteer) => {
        await addVolunteer(volunteer);
        // 重定向或显示成功消息，暂时不知道怎么设计emmm...先用alert吧
        alert('志愿者添加成功!');
    };

    return (
        <div>
            <h1>添加新志愿者</h1>
            <VolunteerForm onSubmit={handleSubmit} />
        </div>
    );
};

export default AddVolunteerPage;
