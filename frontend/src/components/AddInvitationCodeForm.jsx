import { Box, Button, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useOutletContext } from "react-router-dom";
import { addInvitationCode, fetchInvitationCodes } from "../services/api.jsx";

const AddInvitationCodeForm = () => {
    const initialData = "";
    const [invitationCode, setInvitationCode] = useState(initialData);
    const [invitationCodes, setInvitationCodes] = useState([]);
    const { user } = useOutletContext();

    const loadInvitationCodes = async () => {
        const data = await fetchInvitationCodes();
        console.log(data)
        setInvitationCodes(data);
    }
    useEffect(() => {
        loadInvitationCodes();
    }, []);
    const handleChange = (e) => {
        const { value } = e.target;
        setInvitationCode(value);
    };

    const isLegalInvitationCode = code => /^[A-Z0-9]{6}$/.test(code);


    const handleSubmit = async (e) => {
        e.preventDefault();
        if (invitationCodes.includes(invitationCode)) {
            alert("邀请码已存在!");
            return;
        }
        if (!isLegalInvitationCode(invitationCode)) {
            alert("邀请码不符合要求!");
            return;
        }
        await addInvitationCode(invitationCode, user)
        alert("邀请码创建成功!");
    };

    return (
        <Box component="form" onSubmit={handleSubmit} sx={{ padding: '20px' }}>
            <TextField
                fullWidth
                label="新邀请码"
                name="InvitationCode"
                value={invitationCode || ''}
                onChange={handleChange}
                margin="normal"
                required
            />
            <Button type="submit" variant="contained" color="primary" fullWidth>Submit</Button>
        </Box>
    )
}

export default AddInvitationCodeForm;
