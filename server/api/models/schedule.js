const mongoose = require('mongoose');

const scheduleSchema = mongoose.Schema({
    _id: mongoose.Types.ObjectId,
    direction : String,
    time : String,
    id: Number
});

module.exports = mongoose.model('Schedule', scheduleSchema);