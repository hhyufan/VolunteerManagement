package com.example.dao;

import com.example.util.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvitationCodeDaoImpl implements InvitationCodeDao {
    private static final Logger logger = LogManager.getLogger( InvitationCodeDaoImpl.class);
    private final Connection connection = JDBCUtils.getConnection();

    @Override
    public Boolean isInvitationCodeExists(String invitationCode) {
        String query = "SELECT COUNT(*) FROM invitation_codes WHERE code = ? and used = FALSE";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, invitationCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.error("Error checking if invitation code exists: {}", invitationCode, e);
        }
        return false;
    }

    @Override
    public void setInvitationCodeUsed(String invitationCode) {
        String update = "UPDATE invitation_codes SET used = TRUE WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, invitationCode);
            preparedStatement.executeUpdate();
            logger.info("Invitation code marked as used: {}", invitationCode);
        } catch (SQLException e) {
            logger.error("Error marking invitation code as used: {}", invitationCode, e);
        }
    }

    @Override
    public void deleteInvitationCode(String invitationCode) {
        String delete = "DELETE FROM invitation_codes WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, invitationCode);
            preparedStatement.executeUpdate();
            logger.info("Invitation code deleted: {}", invitationCode);
        } catch (SQLException e) {
            logger.error("Error deleting invitation code: {}", invitationCode, e);
        }
    }

    @Override
    public void addInvitationCode(String invitationCode, int userId) {
        String insert = "INSERT INTO invitation_codes (code, user_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, invitationCode);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            logger.info("Invitation code added: {} for user ID: {}", invitationCode, userId);
        } catch (SQLException e) {
            logger.error("Error adding invitation code: {}", invitationCode, e);
        }
    }

    @Override
    public String generateInvitationCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(6);
        // Generate a unique code
        do {
            code.setLength(0); // Clear the StringBuilder
            for (int i = 0; i < 6; i++) {
                code.append(characters.charAt(random.nextInt(characters.length())));
            }
        } while (isInvitationCodeExists(code.toString()));
        logger.info("Generated new invitation code: {}", code.toString());
        return code.toString();
    }

    @Override
    public List<String> getInvitationCodesByAdmin(int id) {
        List<String> invitationCodes = new ArrayList<>();
        String query = "SELECT code FROM invitation_codes WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                invitationCodes.add(resultSet.getString("code"));
            }
            logger.info("Retrieved invitation codes for user ID: {}", id);
        } catch (SQLException e) {
            logger.error("Error retrieving invitation codes for user ID: {}", id, e);
        }
        return invitationCodes;
    }
}
