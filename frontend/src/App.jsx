import ReactDOM from 'react-dom';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import HomePage from './pages/HomePage';
import VolunteerListPage from './pages/VolunteerListPage';
import AddVolunteerPage from './pages/AddVolunteerPage';
import Layout from './layout/Layout.jsx';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import AdminHomePage from "./pages/AdminHomePage.jsx";
import AdminInfoForm from "./components/AdminInfoForm.jsx";
import AddInvitationCodeForm from "./components/AddInvitationCodeForm.jsx";
// 将font矢量图标增加到库中.
library.add(fas);
const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="volunteers" element={<VolunteerListPage />} />
                    <Route path="add-volunteer" element={<AddVolunteerPage />} />
                    <Route path="admin-home" element={<AdminHomePage />} >
                        <Route path="admin-info" element={<AdminInfoForm />} />
                        <Route path="code-add" element={<AddInvitationCodeForm />} />
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;
