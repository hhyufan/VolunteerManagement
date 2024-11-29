import { useEffect, useState } from 'react';
import { useOutletContext } from 'react-router-dom';
import VolunteerCard from '../components/VolunteerCard';
import VolunteerForm from '../components/VolunteerForm';
import { fetchVolunteers, deleteVolunteer, updateVolunteer, addVolunteer } from '../services/api';

const VolunteerListPage = () => {
    const { isLoggedIn } = useOutletContext(); // 从上下文中获取 isLoggedIn
    const [volunteers, setVolunteers] = useState([]);
    const [editingVolunteer, setEditingVolunteer] = useState(null);

    useEffect(() => {
        loadVolunteers();
    }, []);

    const loadVolunteers = async () => {
        const data = await fetchVolunteers();
        setVolunteers(data);  // 将获取的数据存入 volunteers 状态
    };

    const handleDelete = async (id) => {
        await deleteVolunteer(id);
        setEditingVolunteer(null);
        await loadVolunteers();  // 删除后重新加载志愿者列表
    };

    const handleEdit = (volunteer) => {
        setEditingVolunteer(volunteer);
    };

    const handleSubmit = async (volunteer) => {
        if (volunteer.id) {
            await updateVolunteer(volunteer);
        } else {
            await addVolunteer(volunteer);
        }
        setEditingVolunteer(null);
        await loadVolunteers();  // 提交后重新加载志愿者列表
    };

    return (
        <div className="container">
            {volunteers.length > 0 &&editingVolunteer && (
                <VolunteerForm initialData={editingVolunteer} onSubmit={handleSubmit} />
            )}
            <div className="volunteer-list">
                {volunteers.map(volunteer => (
                    <VolunteerCard
                        key={volunteer.id}
                        volunteer={volunteer}
                        onDelete={handleDelete}
                        onEdit={handleEdit}
                        isLoggedIn={isLoggedIn}
                    />
                ))}
            </div>
        </div>
    );
};

export default VolunteerListPage;
