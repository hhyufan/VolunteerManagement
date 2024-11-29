// theme.js
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            main: '#2b8ad9',
        },
        secondary: {
            main: '#f48fb1',
        },
    },
    shape: {
        borderRadius: 8,
    },
});

export default theme;
