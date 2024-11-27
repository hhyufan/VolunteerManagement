package com.example.test;

import com.example.dao.VolunteerDao;
import com.example.dao.VolunteerDaoImpl;
import com.example.entity.Volunteer;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class VolunteerTest extends HttpServlet {

    private VolunteerDao volunteerDao;
    @Before
    public void setUp() {
        volunteerDao = new VolunteerDaoImpl();
    }
    @Test
    public void testAddVolunteer() {
        // 创建一个新的 Volunteer 实例
        Volunteer volunteer = new Volunteer(0, "测试志愿者", "testvolunteer@example.com", "15001234567");

        try {
            // 使用 addVolunteer 方法添加志愿者
            volunteerDao.addVolunteer(volunteer);

            // 查询所有志愿者
            List<Volunteer> volunteers = volunteerDao.getAllVolunteers();

            // 检查是否添加成功
            boolean found = false;
            for (Volunteer v : volunteers) {
                if (v.getName().equals("测试志愿者") &&
                        v.getEmail().equals("testvolunteer@example.com") &&
                        v.getPhone().equals("15001234567")) {
                    found = true;
                    break;
                }
            }

            assertTrue("未找到添加的志愿者", found);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred while adding volunteer: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllVolunteers() {
        try {
            // 获取所有志愿者
            List<Volunteer> volunteers = volunteerDao.getAllVolunteers();

            // 使用 Lambda 表达式遍历列表
            volunteers.forEach(volunteer -> {
                // 在这里进行你想对每个志愿者做的操作
                System.out.println("ID: " + volunteer.getId());
                System.out.println("Name: " + volunteer.getName());
                System.out.println("Email: " + volunteer.getEmail());
                System.out.println("Phone: " + volunteer.getPhone());
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("SQLException occurred while retrieving volunteers: " + e.getMessage());
        }
    }
}
