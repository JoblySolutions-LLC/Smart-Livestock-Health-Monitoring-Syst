const express = require('express');
const bodyParser = require('body-parser');
const app = express();
app.use(bodyParser.json());

app.post('/api/v1/notify', (req, res) => {
  const { deviceId, message, severity } = req.body;
  console.log(`Notify: ${deviceId} - ${message} [${severity}]`);
  res.json({ status: 'sent', deviceId });
});

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => console.log(`Notification service running on ${PORT}`));