// EventCard.jsx
import { Card, CardContent, Typography, CardMedia, Button, Box } from '@mui/material';

const EventCard = ({ event,  onEdit, onDelete, isLoggedIn }) => {
    if (!event) {
        return <Typography variant="h6">Event does not exist!</Typography>;
    }

    const { title, date, location, duration, content, attachmentLink, imageUrl} = event;

    String.prototype.formatDate = function() {
        const match = this.match(/(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2})/);
        // 使用解构赋值提取年份、月份、日期、小时和分钟
        const [, year, month, day, hours, minutes] = match;
        // 返回格式化字符串
        return `${year}年${month}月${day}日 ${hours}时${minutes}分`;
    };

    return (
        <Card sx={{ minWidth: 275, marginBottom: 2 }}>
            {imageUrl && (
                <CardMedia
                    component="img"
                    height="140"
                    image={imageUrl}
                    alt={title}
                />
            )}
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {title}
                </Typography>
                <Typography variant="body2" color="text.secondary">日期: {date.formatDate()}</Typography>
                <Typography variant="body2" color="text.secondary">地点: {location}</Typography>
                <Typography variant="body2" color="text.secondary">时间: {duration}</Typography>
                <Typography variant="body2" color="text.secondary">内容: {content}</Typography>
                <Box sx={{ mt: 2, display: 'flex', flexDirection: 'column' }}>
                    {attachmentLink && (
                        <Button variant="outlined" href={attachmentLink} target="_blank" sx={{ mb: 1 }}>
                            查看附件
                        </Button>
                    )}
                    {isLoggedIn && (
                        <Box sx={{ mt: 2, display: 'flex', justifyContent: 'space-between' }}>
                            <Button size="small" onClick={() => onEdit(event)}>Edit</Button>
                            <Button size="small" onClick={() => onDelete(title)}>Delete</Button>
                        </Box>
                    )}
                </Box>
            </CardContent>
        </Card>
    );
};

export default EventCard;
