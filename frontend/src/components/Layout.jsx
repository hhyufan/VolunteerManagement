// Layout.jsx
import { Link, Outlet } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faUsers, faPlus } from '@fortawesome/free-solid-svg-icons';

const Layout = () => {
    return (
        <div className="layout">
            <header className="header">
                <h1>志愿者管理系统</h1>
            </header>
            <div className="main-content">
                <nav className="sidebar">
                    <Link to="/">
                        <FontAwesomeIcon icon={faHome}/> 首页
                    </Link>
                    <Link to="/volunteers">
                        <FontAwesomeIcon icon={faUsers}/> 管理志愿者
                    </Link>
                    <Link to="/add-volunteer">
                        <FontAwesomeIcon icon={faPlus}/> 添加志愿者
                    </Link>
                </nav>
                <div className="content">
                    <Outlet/>
                </div>
            </div>
            <footer className="footer">
                <p>© 2024 志愿者管理系统</p>
            </footer>
        </div>
    );
};

export default Layout;
